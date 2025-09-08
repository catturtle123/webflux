package com.example.webflux.model.llmclient.gpt.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class GptCompletionRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1812162569186489686L;

    private GPTMessageRole role; // 누가 응답 한건지 (system, ai, )
    private String content;
}
