package com.lotto.roulette.backend.command.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LottoNumberHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private LottoNumber lottoNumber;

    @Column(nullable = false)
    private Long firstPrizeAmount;

    @Column(nullable = false)
    private int winnerCount;

    @Column(nullable = false)
    private LocalDate RoundDate;

    public static LottoNumberHistory create(LottoNumber lottoNumber, Long firstPrizeAmount,
                                            int winnerCount, LocalDate roundDate) {
        return new LottoNumberHistory(lottoNumber, firstPrizeAmount, winnerCount, roundDate);
    }

    private LottoNumberHistory(LottoNumber lottoNumber, Long firstPrizeAmount,
                               int winnerCount, LocalDate roundDate) {
        this.lottoNumber = lottoNumber;
        this.firstPrizeAmount = firstPrizeAmount;
        this.winnerCount = winnerCount;
        this.RoundDate = roundDate;
    }
}
