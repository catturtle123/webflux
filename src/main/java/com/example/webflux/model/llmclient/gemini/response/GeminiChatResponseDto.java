package com.example.webflux.model.llmclient.gemini.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeminiChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6825432784353121165L;

    private List<GeminiCandidate> candidates;

    public String getSingleText() {
        return candidates.stream().findFirst()
                .flatMap(candidate ->
                        candidate.getContent().getParts().stream()
                                .findFirst()
                                .map(GeminiPart::getText)).orElseThrow();
    }
}
