package com.Jee.study_planner.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GroqClient {
    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.url}")
    private String apiUrl;

    private final WebClient webClient;

    public GroqClient(WebClient.Builder builder){
        this.webClient = builder.build();
    }

    public String generate(String systemPrompt, String userPrompt){
        Map<String,Object> requestBody = Map.of(
                "model","llama-3.3-70b-versatile",
                "messages", List.of(
                        Map.of("role","system","content",systemPrompt),
                        Map.of("role","user","content",userPrompt)
                ),
                "temperature",0.7,
                "max_tokens",4096
        );

        Map response = webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map> choices = (List<Map>) response.get("choices");
        Map message = (Map) choices.get(0).get("message");

        return (String) message.get("content");
    }
}
