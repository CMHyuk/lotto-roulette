package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumber;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;

import java.text.NumberFormat;
import java.util.Locale;

public record LotteryWinningHistoryInfo(
        int round,
        int firstLottoNumber,
        int secondLottoNumber,
        int thirdLottoNumber,
        int fourthLottoNumber,
        int fifthLottoNumber,
        int sixthLottoNumber,
        int bonusNumber,
        String firstPrizeAmount,
        int winnerCount
) {

    public LotteryHistory toEntity() {
        LotteryNumber lotteryNumber = LotteryNumber.create(
                firstLottoNumber, secondLottoNumber,
                thirdLottoNumber, fourthLottoNumber,
                fifthLottoNumber, sixthLottoNumber
        );
        return LotteryHistory.create(lotteryNumber, Long.parseLong(firstPrizeAmount), winnerCount, round);
    }

    public static LotteryWinningHistoryInfo from(LotteryWinningHistoryResponse response) {
        return new LotteryWinningHistoryInfo(
                response.drwNo(), response.drwtNo1(),
                response.drwtNo2(), response.drwtNo3(),
                response.drwtNo4(), response.drwtNo5(),
                response.drwtNo6(), response.bnusNo(),
                convertToKRWFormat(response.firstWinamnt()), response.firstPrzwnerCo()
                );
    }

    public static String convertToKRWFormat(Long firstPrizeAmount) {
        NumberFormat koreanFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return koreanFormat.format(firstPrizeAmount);
    }
}

