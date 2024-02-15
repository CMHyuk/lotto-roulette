package com.lotto.roulette.backend.command.infrastructure.chatgpt;

public record LottoNumberResponse(String result) {

    public static LottoNumberResponse from(ChatGptResponse response) {
        return new LottoNumberResponse(response.getText());
    }
}
