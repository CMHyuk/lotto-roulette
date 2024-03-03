package com.lotto.roulette.backend.query.dto;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;

public record LotteryHistoryResponse(
        Integer firstLotteryNumber,
        Integer secondLotteryNumber,
        Integer thirdLotteryNumber,
        Integer fourthLotteryNumber,
        Integer fifthLotteryNumber,
        Integer sixthLotteryNumber,
        Long firstPrizeAmount,
        Integer winnerCount,
        Integer round
) {

    public LotteryHistoryResponse(LotteryHistory lotteryHistory) {
        this(lotteryHistory.getLotteryNumber().getFirstLotteryNumber(),
                lotteryHistory.getLotteryNumber().getSecondLotteryNumber(),
                lotteryHistory.getLotteryNumber().getThirdLotteryNumber(),
                lotteryHistory.getLotteryNumber().getFourthLotteryNumber(),
                lotteryHistory.getLotteryNumber().getFifthLotteryNumber(),
                lotteryHistory.getLotteryNumber().getSixthLotteryNumber(),
                lotteryHistory.getFirstPrizeAmount(),
                lotteryHistory.getWinnerCount(),
                lotteryHistory.getWinnerCount()
        );
    }

    public static LotteryHistoryResponse createEmpty() {
        return new LotteryHistoryResponse(null, null, null,
                null, null, null,
                null, null, null);
    }
}
