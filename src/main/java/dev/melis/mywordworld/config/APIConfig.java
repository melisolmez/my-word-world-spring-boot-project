package dev.melis.mywordworld.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class APIConfig {

    @Bean
    public WebClient webClient(@Value("${wordnik.api.base.url}") String baseUrl) {
        return WebClient.builder()
                        .baseUrl(baseUrl)
                        .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
