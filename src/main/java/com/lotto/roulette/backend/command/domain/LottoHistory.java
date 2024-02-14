package com.lotto.roulette.backend.command.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LottoHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private LottoNumber lottoNumber;

    @Column(nullable = false)
    private String firstPrizeAmount;

    @Column(nullable = false)
    private Integer winnerCount;

    @Column(nullable = false)
    private String roundDate;

    @Column(nullable = false)
    private Integer round;

    public static LottoHistory create(LottoNumber lottoNumber, String firstPrizeAmount,
                                      Integer winnerCount, String roundDate, Integer round) {
        return new LottoHistory(lottoNumber, firstPrizeAmount, winnerCount, roundDate, round);
    }

    private LottoHistory(LottoNumber lottoNumber, String firstPrizeAmount,
                         Integer winnerCount, String roundDate, Integer round) {
        this.lottoNumber = lottoNumber;
        this.firstPrizeAmount = firstPrizeAmount;
        this.winnerCount = winnerCount;
        this.roundDate = roundDate;
        this.round = round;
    }
}
