package com.vcube.healthcare.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.vcube.healthcare.ai.AIRequestDTO;
import com.vcube.healthcare.ai.AIResponseDTO;
import com.vcube.healthcare.ai.AIService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final Client geminiClient;

    @Value("${gemini.model}")
    private String model;

    @Override
    public AIResponseDTO getAIResponse(AIRequestDTO request) {

        String prompt = """
                You are a healthcare information assistant.
                Answer general healthcare questions clearly.
                Do not diagnose diseases or prescribe medicines.
                Encourage users to consult qualified doctors
                for personal medical concerns.

                User question: 
                """ + request.getMessage();

        String response = geminiClient.models
                .generateContent(model, prompt, null)
                .text();

        return new AIResponseDTO(response);
    }
}