package com.lotto.roulette.backend.query.repository;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LotteryHistoryQueryRepository extends JpaRepository<LotteryHistory, Long> {
    Optional<LotteryHistory> findTopByOrderByFirstPrizeAmountDesc();


}
