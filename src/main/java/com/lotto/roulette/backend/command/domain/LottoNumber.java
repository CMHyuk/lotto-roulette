package com.lotto.roulette.backend.command.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LottoNumber {

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

    public static LottoNumber create(int firstLottoNumber, int secondLottoNumber, int thirdLottoNumber,
                                     int fourthLottoNumber, int fifthLottoNumber, int sixthLottoNumber) {
        return new LottoNumber(firstLottoNumber, secondLottoNumber, thirdLottoNumber,
                fourthLottoNumber, fifthLottoNumber, sixthLottoNumber);
    }

    private LottoNumber(int firstLottoNumber, int secondLottoNumber, int thirdLottoNumber,
                       int fourthLottoNumber, int fifthLottoNumber, int sixthLottoNumber) {
        this.firstLottoNumber = firstLottoNumber;
        this.secondLottoNumber = secondLottoNumber;
        this.thirdLottoNumber = thirdLottoNumber;
        this.fourthLottoNumber = fourthLottoNumber;
        this.fifthLottoNumber = fifthLottoNumber;
        this.sixthLottoNumber = sixthLottoNumber;
    }
}
