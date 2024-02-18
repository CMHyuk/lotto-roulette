package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

public record LotteryWinningHistoryResponse(
        String returnValue,
        Long totSellamnt,
        String drwNoDate,
        Long firstWinamnt,
        int firstPrzwnerCo,
        int drwtNo1,
        int drwtNo2,
        int drwtNo3,
        int drwtNo4,
        int drwtNo5,
        int drwtNo6,
        int bnusNo,
        int drwNo,
        Long firstAccumamnt
) {
}
