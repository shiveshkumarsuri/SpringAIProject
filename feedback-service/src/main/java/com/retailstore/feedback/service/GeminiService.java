package com.retailstore.feedback.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for interacting with Google's Gemini AI using REST API
 */
@Service
public class GeminiService {

    @Value("${gemini.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Use v1beta API with gemini-1.5-pro (stable and widely available)
    // Alternative models: gemini-1.5-flash (faster, lower quality), gemini-pro-vision (for images)
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro:generateContent";

    /**
     * Sends a prompt to Gemini and returns the response
     *
     * @param prompt The prompt to send to Gemini
     * @return The response from Gemini
     */
    public String generateContent(String prompt) {
        try {
            // Create the request body using Jackson
            ObjectNode requestBody = objectMapper.createObjectNode();
            ArrayNode contents = objectMapper.createArrayNode();
            ObjectNode content = objectMapper.createObjectNode();
            ArrayNode parts = objectMapper.createArrayNode();
            ObjectNode textPart = objectMapper.createObjectNode();

            textPart.put("text", prompt);
            parts.add(textPart);
            content.set("parts", parts);
            contents.add(content);
            requestBody.set("contents", contents);

            // Add generation config for better JSON responses
            ObjectNode generationConfig = objectMapper.createObjectNode();
            generationConfig.put("temperature", 0.7);
            generationConfig.put("maxOutputTokens", 1024);
            requestBody.set("generationConfig", generationConfig);

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create the URL with API key
            String url = GEMINI_API_URL + "?key=" + apiKey;

            // Create the request
            HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);

            // Send the request
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            // Parse the response
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ObjectNode responseJson = (ObjectNode) objectMapper.readTree(response.getBody());
                ArrayNode candidates = (ArrayNode) responseJson.get("candidates");

                if (candidates != null && candidates.size() > 0) {
                    ObjectNode candidate = (ObjectNode) candidates.get(0);
                    ObjectNode candidateContent = (ObjectNode) candidate.get("content");
                    ArrayNode candidateParts = (ArrayNode) candidateContent.get("parts");

                    if (candidateParts != null && candidateParts.size() > 0) {
                        return candidateParts.get(0).get("text").asText();
                    }
                }
            }

            return "No response from Gemini";

        } catch (Exception e) {
            System.err.println("Error calling Gemini API: " + e.getMessage());
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Test method to verify API connection
     */
    public void testConnection() {
        String testPrompt = "Say 'Hello, World!' if you can hear me.";
        String response = generateContent(testPrompt);
        System.out.println("Gemini API Test Response: " + response);
    }
}
