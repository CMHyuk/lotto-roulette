package com.lotto.roulette.backend.command.lotteryhistory.dto;

import java.util.List;
import java.util.stream.Collectors;

public record LotteryHistoryNumbersDto(
        int firstLotteryNumber,
        int secondLotteryNumber,
        int thirdLotteryNumber,
        int fourthLotteryNumber,
        int fifthLotteryNumber,
        int sixthLotteryNumber
) {
    public static List<Integer> toLotteryHistoryNumbers(List<LotteryHistoryNumbersDto> lotteryHistoryNumbers) {
        return lotteryHistoryNumbers.stream()
                .map(LotteryHistoryNumbersDto::toList)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Integer> toList() {
        return List.of(firstLotteryNumber, secondLotteryNumber, thirdLotteryNumber,
                fourthLotteryNumber, fifthLotteryNumber, sixthLotteryNumber);
    }
}
