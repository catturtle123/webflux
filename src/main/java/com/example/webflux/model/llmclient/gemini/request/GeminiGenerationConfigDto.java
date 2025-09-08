package com.example.webflux.model.llmclient.gemini.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Data
public class GeminiGenerationConfigDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -59946915576909243L;

    private String responseMimeType;

    public GeminiGenerationConfigDto() {
        this.responseMimeType = "application/json";
    }
}
