package com.lotto.roulette.backend.command.dto;

import com.lotto.roulette.backend.command.domain.LotteryNumber;
import com.lotto.roulette.backend.command.domain.LotteryHistory;
import com.lotto.roulette.backend.command.infrastructure.lotto.LotteryWinningHistoryResponse;

import java.text.NumberFormat;
import java.util.Locale;

public record LotteryWinningHistoryInfo(
        String roundDate,
        String firstPrizeAmount,
        int winnerCount,
        int firstLottoNumber,
        int secondLottoNumber,
        int thirdLottoNumber,
        int fourthLottoNumber,
        int fifthLottoNumber,
        int sixthLottoNumber,
        int bonusNumber,
        int round
) {

    public LotteryHistory toEntity() {
        LotteryNumber lotteryNumber = LotteryNumber.create(
                firstLottoNumber, secondLottoNumber,
                thirdLottoNumber, fourthLottoNumber,
                fifthLottoNumber, sixthLottoNumber
        );
        return LotteryHistory.create(lotteryNumber, Long.parseLong(firstPrizeAmount), winnerCount, roundDate, round);
    }

    public static LotteryWinningHistoryInfo from(LotteryWinningHistoryResponse response) {
        return new LotteryWinningHistoryInfo(
                response.drwNoDate(), convertToKRWFormat(response.firstWinamnt()),
                response.firstPrzwnerCo(), response.drwtNo1(),
                response.drwtNo2(), response.drwtNo3(),
                response.drwtNo4(), response.drwtNo5(),
                response.drwtNo6(), response.bnusNo(),
                response.drwNo());
    }

    public static String convertToKRWFormat(Long firstPrizeAmount) {
        NumberFormat koreanFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return koreanFormat.format(firstPrizeAmount);
    }
}

