package ayandhiman.projects.ai_gateway.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GroqClientConfig {

    @Value("${groq.api.url}")
    private String baseUrl;

    @Value("${groq.api.key}")
    private String apiKey;

    @Bean
    public WebClient groqWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

}
