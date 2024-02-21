package com.lotto.roulette.backend.command.lotteryhistory.dto;

import java.util.List;

public record LotteryHistoryApiResponse(
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

    public List<Integer> getLotteryNumbers() {
        return List.of(drwtNo1, drwtNo2, drwtNo3, drwtNo4, drwtNo5, drwtNo6);
    }
}
