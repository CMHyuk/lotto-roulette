package com.lotto.roulette.backend.command.lotteryhistory.domain;

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
    private String firstPrizeAmount;

    @Column(nullable = false)
    private Integer winnerCount;

    @Column(nullable = false)
    private Integer round;

    public static LotteryHistory create(LotteryNumber lotteryNumber, String firstPrizeAmount,
                                        Integer winnerCount, Integer round) {
        return new LotteryHistory(lotteryNumber, firstPrizeAmount, winnerCount, round);
    }

    private LotteryHistory(LotteryNumber lotteryNumber, String firstPrizeAmount,
                           Integer winnerCount, Integer round) {
        this.lotteryNumber = lotteryNumber;
        this.firstPrizeAmount = firstPrizeAmount;
        this.winnerCount = winnerCount;
        this.round = round;
    }
}
