package com.lotto.roulette.backend.command.infrastructure;

import lombok.Getter;

@Getter
public enum LotteryConst {

    CHATGPT("chatgpt"), CUSTOM("custom");

    private final String name;

    LotteryConst(String name) {
        this.name = name;
    }
}
