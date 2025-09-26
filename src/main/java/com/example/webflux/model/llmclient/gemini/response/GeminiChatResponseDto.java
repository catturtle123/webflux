package com.example.webflux.model.llmclient.gemini.response;

import com.example.webflux.exception.CustomErrorType;
import com.example.webflux.exception.ErrorTypeException;
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
                                .map(GeminiPart::getText)).orElseThrow(() -> {
                                    return new ErrorTypeException("[GeminiResponse] There is no candidates.", CustomErrorType.GEMINI_RESPONSE_ERROR);
                });
    }
}
