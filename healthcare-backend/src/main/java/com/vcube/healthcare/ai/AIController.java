package com.vcube.healthcare.ai;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vcube.healthcare.ai.AIRequestDTO;
import com.vcube.healthcare.ai.AIResponseDTO;
import com.vcube.healthcare.ai.AIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @PostMapping("/chat")
    public ResponseEntity<AIResponseDTO> chat(
            @RequestBody AIRequestDTO request) {

        return ResponseEntity.ok(aiService.getAIResponse(request));
    }
}