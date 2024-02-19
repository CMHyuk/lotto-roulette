package com.lotto.roulette.backend.command.lotteryhistory.domain;

import com.lotto.roulette.backend.command.lotteryhistory.exception.LotteryHistoryException;
import com.lotto.roulette.backend.common.exception.BusinessException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LotteryNumber {

    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 45;

    @Column(nullable = false)
    private int firstLotteryNumber;

    @Column(nullable = false)
    private int secondLotteryNumber;

    @Column(nullable = false)
    private int thirdLotteryNumber;

    @Column(nullable = false)
    private int fourthLotteryNumber;

    @Column(nullable = false)
    private int fifthLotteryNumber;

    @Column(nullable = false)
    private int sixthLotteryNumber;

    public static LotteryNumber create(int firstLotteryNumber, int secondLotteryNumber, int thirdLotteryNumber,
                                       int fourthLotteryNumber, int fifthLotteryNumber, int sixthLotteryNumber) {
        return new LotteryNumber(firstLotteryNumber, secondLotteryNumber, thirdLotteryNumber,
                fourthLotteryNumber, fifthLotteryNumber, sixthLotteryNumber);
    }

    private LotteryNumber(int firstLotteryNumber, int secondLotteryNumber, int thirdLotteryNumber,
                          int fourthLotteryNumber, int fifthLotteryNumber, int sixthLotteryNumber) {
        validateLottoNumberRange(firstLotteryNumber);
        validateLottoNumberRange(secondLotteryNumber);
        validateLottoNumberRange(thirdLotteryNumber);
        validateLottoNumberRange(fourthLotteryNumber);
        validateLottoNumberRange(fifthLotteryNumber);
        validateLottoNumberRange(sixthLotteryNumber);
        this.firstLotteryNumber = firstLotteryNumber;
        this.secondLotteryNumber = secondLotteryNumber;
        this.thirdLotteryNumber = thirdLotteryNumber;
        this.fourthLotteryNumber = fourthLotteryNumber;
        this.fifthLotteryNumber = fifthLotteryNumber;
        this.sixthLotteryNumber = sixthLotteryNumber;
    }

    private static void validateLottoNumberRange(int number) {
        if (number < FIRST_NUMBER || number > LAST_NUMBER) {
            throw BusinessException.from(LotteryHistoryException.INVALID_LOTTERY_NUMBER);
        }
    }
}
