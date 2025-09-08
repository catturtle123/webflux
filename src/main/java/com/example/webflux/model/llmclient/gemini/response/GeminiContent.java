package com.example.webflux.model.llmclient.gemini.response;

import com.example.webflux.model.llmclient.gemini.request.GeminiMessageRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeminiContent implements Serializable {
    @Serial
    private static final long serialVersionUID = 6530370678908722858L;

    private List<GeminiPart> parts;
    private GeminiMessageRole role;

    public GeminiContent(List<GeminiPart> parts) {
        this.parts = parts;
    }
}
