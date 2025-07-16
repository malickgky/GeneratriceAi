package com.example.generatriceaai.controllers;

import com.example.generatriceaai.ouputs.CinModel;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RestController
public class MultiModalController {
    private final ChatClient chatClient;
    @Value("classpath:/images/cin.jpg")
    private Resource image;

    public MultiModalController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    @GetMapping("/describe")
    public CinModel describeImage() {
        return chatClient.prompt()
                .system("Donne une description de l'image fournie")
                .user(u-> u.text("Décrire cette image ")
                ).call()
                .entity(CinModel.class);

    }
}
