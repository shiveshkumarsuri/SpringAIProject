package com.shivesh.ai.java.controllers;

import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;

@RestController
@RequestMapping
public class GeminiChatController {

    private ChatClient chatClient;

//    public GeminiChatController(ChatClient.Builder builder){
//        this.chatClient = builder.build();
//    }

    public GeminiChatController(GoogleGenAiChatModel chatModel) {
        // Create the client manually from the model
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/gemini-chat")
    public ResponseEntity<String> getGeminiResponse(@RequestParam(value="q", required = true) String q){

        var chatResponse = chatClient.prompt(q).call().content();
        return ResponseEntity.ok(chatResponse);

    }
}
