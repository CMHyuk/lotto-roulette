package com.lotto.roulette.backend.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryHistoryRepository extends JpaRepository<LotteryHistory, Long> {
}
