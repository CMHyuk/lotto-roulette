package com.lotto.roulette.backend.command.infrastructure.chatgpt;

public record ChatgptRequest(String model, String prompt, float temperature) {
}
