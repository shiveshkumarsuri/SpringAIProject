package com.shivesh.ai_java.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OpenAIChatController {

    private ChatClient chatClient;

//    public OpenAIChatController(ChatClient.Builder builder) {
//        this.chatClient= builder.build();
//    }

    public OpenAIChatController(OpenAiChatModel chatModel) {
        // Create the client manually from the model
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value="q", required = true) String q){

        var chatResponse= chatClient.prompt(q).call().content();
        return ResponseEntity.ok(chatResponse);
    }
}
