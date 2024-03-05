package com.lotto.roulette.backend.command.lotteryhistory.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "IDX_ROUND", columnList = "round"),
        uniqueConstraints = {@UniqueConstraint(
                name = "uc_lottery_history_round",
                columnNames = {"round"}
        )})
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
    private Integer round;

    public static LotteryHistory create(LotteryNumber lotteryNumber, Long firstPrizeAmount,
                                        Integer winnerCount, Integer round) {
        return new LotteryHistory(lotteryNumber, firstPrizeAmount, winnerCount, round);
    }

    private LotteryHistory(LotteryNumber lotteryNumber, Long firstPrizeAmount,
                           Integer winnerCount, Integer round) {
        this.lotteryNumber = lotteryNumber;
        this.firstPrizeAmount = firstPrizeAmount;
        this.winnerCount = winnerCount;
        this.round = round;
    }
}
