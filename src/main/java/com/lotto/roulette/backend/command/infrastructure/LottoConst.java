package com.lotto.roulette.backend.command.infrastructure;

import lombok.Getter;

@Getter
public enum LottoConst {

    CHATGPT("chatgpt"), CUSTOM("custom");

    private final String name;

    LottoConst(String name) {
        this.name = name;
    }
}
