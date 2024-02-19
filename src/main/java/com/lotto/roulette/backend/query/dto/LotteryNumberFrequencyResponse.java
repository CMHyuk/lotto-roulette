package com.lotto.roulette.backend.query.dto;

public record LotteryNumberFrequencyResponse(int lotteryNumber, int frequency) {

    public static LotteryNumberFrequencyResponse of(int lotteryNumber, int frequency) {
        return new LotteryNumberFrequencyResponse(lotteryNumber, frequency);
    }
}
