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
    private int firstLottoNumber;

    @Column(nullable = false)
    private int secondLottoNumber;

    @Column(nullable = false)
    private int thirdLottoNumber;

    @Column(nullable = false)
    private int fourthLottoNumber;

    @Column(nullable = false)
    private int fifthLottoNumber;

    @Column(nullable = false)
    private int sixthLottoNumber;

    public static LotteryNumber create(int firstLottoNumber, int secondLottoNumber, int thirdLottoNumber,
                                       int fourthLottoNumber, int fifthLottoNumber, int sixthLottoNumber) {
        return new LotteryNumber(firstLottoNumber, secondLottoNumber, thirdLottoNumber,
                fourthLottoNumber, fifthLottoNumber, sixthLottoNumber);
    }

    private LotteryNumber(int firstLottoNumber, int secondLottoNumber, int thirdLottoNumber,
                          int fourthLottoNumber, int fifthLottoNumber, int sixthLottoNumber) {
        validateLottoNumberRange(firstLottoNumber);
        validateLottoNumberRange(secondLottoNumber);
        validateLottoNumberRange(thirdLottoNumber);
        validateLottoNumberRange(fourthLottoNumber);
        validateLottoNumberRange(fifthLottoNumber);
        validateLottoNumberRange(sixthLottoNumber);
        this.firstLottoNumber = firstLottoNumber;
        this.secondLottoNumber = secondLottoNumber;
        this.thirdLottoNumber = thirdLottoNumber;
        this.fourthLottoNumber = fourthLottoNumber;
        this.fifthLottoNumber = fifthLottoNumber;
        this.sixthLottoNumber = sixthLottoNumber;
    }

    private static void validateLottoNumberRange(int number) {
        if (number < FIRST_NUMBER || number > LAST_NUMBER) {
            throw BusinessException.from(LotteryHistoryException.INVALID_LOTTERY_NUMBER);
        }
    }
}
