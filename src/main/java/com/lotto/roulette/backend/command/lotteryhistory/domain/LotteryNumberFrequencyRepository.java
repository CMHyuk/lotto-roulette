package com.lotto.roulette.backend.command.lotteryhistory.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LotteryNumberFrequencyRepository extends JpaRepository<LotteryNumberFrequency, Long> {
    Optional<LotteryNumberFrequency> findByLotteryNumber(Integer lotteryNumber);
}
