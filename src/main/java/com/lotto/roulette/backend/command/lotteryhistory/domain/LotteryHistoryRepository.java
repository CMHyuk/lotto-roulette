package com.lotto.roulette.backend.command.lotteryhistory.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LotteryHistoryRepository extends JpaRepository<LotteryHistory, Long> {
    @Query("SELECT round FROM LotteryHistory ORDER BY round DESC LIMIT 1")
    Integer findLatestRound();
}
