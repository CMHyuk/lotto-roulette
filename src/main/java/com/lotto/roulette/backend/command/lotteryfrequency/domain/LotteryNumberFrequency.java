package com.lotto.roulette.backend.command.lotteryfrequency.domain;

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

    private Integer lottoNumber;
    private Integer frequency;

    public void increaseFrequency() {
        this.frequency++;
    }

    public LotteryNumberFrequency(Integer lottoNumber) {
        this.lottoNumber = lottoNumber;
        this.frequency = DEFAULT_FREQUENCY;
    }
}
