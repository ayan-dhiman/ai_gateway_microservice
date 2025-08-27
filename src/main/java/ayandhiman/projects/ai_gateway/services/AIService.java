package ayandhiman.projects.ai_gateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class AIService {

    private final WebClient webClient;

    @Value("${groq.api.model}")
    private String model;

    public AIService(WebClient groqWebClient) {
        this.webClient = groqWebClient;
    }

    public Mono<String> askAI(String prompt) {

        Map<String, Object> requestBodyMap = Map.of(
                "model", model,
                "messages", new Object[]{
                        Map.of("role", "user", "content", prompt)
                }
        );

        return webClient.post()
                .bodyValue(requestBodyMap)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (java.util.List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return (String) message.get("content");
                    }
                    return "No response from AI.";
                });
    }

}
