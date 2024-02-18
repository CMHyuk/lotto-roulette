package com.lotto.roulette.backend.command.lotterynumber.infrastructure;

import lombok.Getter;

@Getter
public enum LotteryGeneratorConst {

    CHATGPT("chatgpt"), CUSTOM("custom");

    private final String name;

    LotteryGeneratorConst(String name) {
        this.name = name;
    }
}
