package com.lotto.roulette.backend.command.dto;

import com.lotto.roulette.backend.command.domain.LottoNumber;
import com.lotto.roulette.backend.command.domain.LottoHistory;
import com.lotto.roulette.backend.command.infrastructure.lotto.LottoWinnerApiResponse;

import java.text.NumberFormat;
import java.util.Locale;

public record LottoWinnerInfo(
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

    public LottoHistory toEntity() {
        LottoNumber lottoNumber = LottoNumber.create(
                firstLottoNumber, secondLottoNumber,
                thirdLottoNumber, fourthLottoNumber,
                fifthLottoNumber, sixthLottoNumber
        );
        return LottoHistory.create(lottoNumber, firstPrizeAmount, winnerCount, roundDate, round);
    }

    public static LottoWinnerInfo from(LottoWinnerApiResponse response) {
        return new LottoWinnerInfo(
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

