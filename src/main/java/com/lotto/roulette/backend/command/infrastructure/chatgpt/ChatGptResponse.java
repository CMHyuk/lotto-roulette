package com.lotto.roulette.backend.command.infrastructure.chatgpt;

public record ChatGptResponse(Choices[] choices) {

    public record Choices(String text) {
    }

    public String getText() {
        return (choices != null && choices.length > 0) ? choices[0].text() : null;
    }
}
