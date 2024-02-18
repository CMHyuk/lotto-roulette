package com.lotto.roulette.backend.command.lotterynumber.infrastructure.chatgpt;

public record ChatGptRequest(String model, String prompt, float temperature) {

    public static ChatGptRequest of(String model, String prompt, float temperature) {
        return new ChatGptRequest(model, prompt, temperature);
    }
}
