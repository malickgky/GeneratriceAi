package com.example.generatriceaai.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;

@RestController
public class StreamingController {
    private ChatClient chatClient;

    public StreamingController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();

    }
@GetMapping(value = "/stream", produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> streaming(String query) {
        return chatClient.prompt()
                .user(query)
                .stream()
                .content();

    }
    @GetMapping("/nonstream")
    public String nonstream(String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .content();

    }
}
