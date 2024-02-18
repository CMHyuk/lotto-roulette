package com.lotto.roulette.backend.command.lotteryhistory.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LotteryNumber {

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
        this.firstLottoNumber = firstLottoNumber;
        this.secondLottoNumber = secondLottoNumber;
        this.thirdLottoNumber = thirdLottoNumber;
        this.fourthLottoNumber = fourthLottoNumber;
        this.fifthLottoNumber = fifthLottoNumber;
        this.sixthLottoNumber = sixthLottoNumber;
    }
}
