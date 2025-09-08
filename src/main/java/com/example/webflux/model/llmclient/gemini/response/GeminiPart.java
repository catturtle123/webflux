package com.example.webflux.model.llmclient.gemini.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GeminiPart implements Serializable {
    @Serial
    private static final long serialVersionUID = 6255190760075077095L;

    private String text;
}
