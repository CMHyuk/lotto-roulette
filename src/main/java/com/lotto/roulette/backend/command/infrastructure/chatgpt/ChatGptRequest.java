package com.lotto.roulette.backend.command.infrastructure.chatgpt;

public record ChatGptRequest(String model, String prompt, float temperature) {
}
