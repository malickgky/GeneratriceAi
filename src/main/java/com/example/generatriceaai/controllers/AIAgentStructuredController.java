package com.example.generatriceaai.controllers;

import com.example.generatriceaai.ouputs.Movie;
import com.example.generatriceaai.ouputs.MovieList;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.query.results.Builders.entity;

@RestController
public class AIAgentStructuredController {
    private ChatClient chatClient;
    public AIAgentStructuredController(ChatClient.Builder  chatClient) {
        this.chatClient = chatClient.build();
    }
    @GetMapping("/askAgent")
    public MovieList asKLLM(String query){
        String systemMessage = """
                Vous etes un spécialiste dans le domaine du cinéma 
                Répond à la question de l'utilisateur à ce propos
                """;
        return chatClient.prompt()
                .system(systemMessage)
                .user(query)
                .call()
                .entity(MovieList.class);


    }
}
