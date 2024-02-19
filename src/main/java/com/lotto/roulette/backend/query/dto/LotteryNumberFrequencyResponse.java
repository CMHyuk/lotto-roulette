package com.lotto.roulette.backend.query.dto;

public record LotteryNumberFrequencyResponse(int lottoNumber, int frequency) {

    public static LotteryNumberFrequencyResponse of(int lottoNumber, int frequency) {
        return new LotteryNumberFrequencyResponse(lottoNumber, frequency);
    }
}
