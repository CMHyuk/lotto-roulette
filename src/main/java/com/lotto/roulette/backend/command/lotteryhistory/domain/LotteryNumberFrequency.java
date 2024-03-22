package com.lotto.roulette.backend.command.lotteryhistory.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LotteryNumberFrequency {

    private static final int DEFAULT_FREQUENCY = 0;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer lotteryNumber;

    @Column(nullable = false)
    private Integer frequency;
}
