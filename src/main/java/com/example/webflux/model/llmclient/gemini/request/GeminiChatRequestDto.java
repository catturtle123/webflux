package com.example.webflux.model.llmclient.gemini.request;

import com.example.webflux.model.llmclient.LlmChatRequestDto;
import com.example.webflux.model.llmclient.gemini.response.GeminiContent;
import com.example.webflux.model.llmclient.gemini.response.GeminiPart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeminiChatRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -2963594048483701525L;

    private List<GeminiContent> contents;
    private GeminiContent systemInstruction;
    private GeminiGenerationConfigDto generationConfig;

    public GeminiChatRequestDto(LlmChatRequestDto llmChatRequestDto) {
        if (llmChatRequestDto.isUseJson()) {
            this.generationConfig = new GeminiGenerationConfigDto();
        }

        this.contents = List.of(
                new GeminiContent(List.of(new GeminiPart(llmChatRequestDto.getUserRequest())), GeminiMessageRole.USER)
        );
        this.systemInstruction = new GeminiContent(List.of(new GeminiPart(llmChatRequestDto.getUserRequest())));
    }
}
