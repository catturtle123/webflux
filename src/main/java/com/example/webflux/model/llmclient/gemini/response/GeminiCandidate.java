package com.example.webflux.model.llmclient.gemini.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeminiCandidate implements Serializable {
    @Serial
    private static final long serialVersionUID = -3164998536829043532L;

    private GeminiContent content;
    private String finishReason;
}
