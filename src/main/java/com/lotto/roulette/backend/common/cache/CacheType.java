package com.lotto.roulette.backend.common.cache;

import lombok.Getter;

@Getter
public enum CacheType {

    TOP_PRIZE("topPrize", 24 * 60 * 60, 1),
    LOTTERY_HISTORY("lotteryHistory", 24 * 60 * 60, 2000),
    LOTTERY_NUMBER_FREQUENCY("LotteryNumberFrequency", 24 * 60 * 60, 6),
    LOTTERY_NUMBER_TOP_SIX("LotteryNumberTopSix", 24 * 60 * 60, 1);

    private final String cacheName;
    private final int expiredAfterWrite;
    private final int maximumSize;

    CacheType(String cacheName, int expiredAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expiredAfterWrite = expiredAfterWrite;
        this.maximumSize = maximumSize;
    }
}
