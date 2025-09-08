package com.example.webflux.model.llmclient;

import com.example.webflux.model.user.chat.UserChatRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LlmChatRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3676573231559664805L;

    private String userRequest;

    /**
     * systemPrompt가 userRequest에 포함 되는 내용보다 더 높은 강제성과 우선순위를 가집니다.
     */
    private String systemPrompt;

    /**
     * 높은 우선 순위로 json으로 응답이 옴
     */
    private boolean useJson;

    private LlmModel llmModel;

    public LlmChatRequestDto(UserChatRequestDto userChatRequestDto, String systemPrompt) {
        this.systemPrompt = systemPrompt;
        this.userRequest = userChatRequestDto.getRequest();
        this.llmModel = userChatRequestDto.getLlmModel();
    }
}
