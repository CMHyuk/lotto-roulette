package com.lotto.roulette.backend.query.repository;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumberFrequency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryNumberFrequencyQueryRepository extends JpaRepository<LotteryNumberFrequency, Long> {
}
