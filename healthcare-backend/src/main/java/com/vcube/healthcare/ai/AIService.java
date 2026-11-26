package com.vcube.healthcare.ai;

import com.vcube.healthcare.ai.AIRequestDTO;
import com.vcube.healthcare.ai.AIResponseDTO;

public interface AIService {

    AIResponseDTO getAIResponse(AIRequestDTO request);
}