package ayandhiman.projects.ai_gateway.controllers;

import ayandhiman.projects.ai_gateway.services.AIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public Mono<Map<String, String>> askAI(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        System.out.println("Prompt: "+prompt);
        Mono<Map<String, String>> ai_response = aiService.askAI(prompt)
                .map(response -> Map.of("response", response));
        System.out.println("Response: "+ai_response);
        return ai_response;
    }

}
