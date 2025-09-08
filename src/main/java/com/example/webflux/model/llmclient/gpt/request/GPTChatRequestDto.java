package com.example.webflux.model.llmclient.gpt.request;

import com.example.webflux.model.llmclient.LlmChatRequestDto;
import com.example.webflux.model.llmclient.LlmModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GPTChatRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 6553236843017158940L;

    private List<GptCompletionRequestDto> messages;
    private LlmModel model;
    private Boolean stream;
    private GptResponseFormat response_format;

    public GPTChatRequestDto(LlmChatRequestDto llmChatRequestDto) {
        if (llmChatRequestDto.isUseJson()) {
            this.response_format = new GptResponseFormat("json_object");
        }

        this.messages = List.of(
                new GptCompletionRequestDto(GPTMessageRole.SYSTEM, llmChatRequestDto.getSystemPrompt()),
                new GptCompletionRequestDto(GPTMessageRole.USER, llmChatRequestDto.getUserRequest())
        );
        this.model = llmChatRequestDto.getLlmModel();
    }
}
