package com.lotto.roulette.backend.command.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LotteryHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private LotteryNumber lotteryNumber;

    @Column(nullable = false)
    private Long firstPrizeAmount;

    @Column(nullable = false)
    private Integer winnerCount;

    @Column(nullable = false)
    private String roundDate;

    @Column(nullable = false)
    private Integer round;

    public static LotteryHistory create(LotteryNumber lotteryNumber, Long firstPrizeAmount,
                                        Integer winnerCount, String roundDate, Integer round) {
        return new LotteryHistory(lotteryNumber, firstPrizeAmount, winnerCount, roundDate, round);
    }

    private LotteryHistory(LotteryNumber lotteryNumber, Long firstPrizeAmount,
                           Integer winnerCount, String roundDate, Integer round) {
        this.lotteryNumber = lotteryNumber;
        this.firstPrizeAmount = firstPrizeAmount;
        this.winnerCount = winnerCount;
        this.roundDate = roundDate;
        this.round = round;
    }
}
