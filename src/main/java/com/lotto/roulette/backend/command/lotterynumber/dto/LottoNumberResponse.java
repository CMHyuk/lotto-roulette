package com.lotto.roulette.backend.command.lotterynumber.dto;

import com.lotto.roulette.backend.command.lotterynumber.infrastructure.chatgpt.ChatGptResponse;

public record LottoNumberResponse(String result) {

    public static LottoNumberResponse from(ChatGptResponse response) {
        return new LottoNumberResponse(response.getText());
    }
}
