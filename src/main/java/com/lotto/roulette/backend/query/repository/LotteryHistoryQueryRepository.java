package com.lotto.roulette.backend.query.repository;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LotteryHistoryQueryRepository extends JpaRepository<LotteryHistory, Long> {

    @Query("SELECT MAX(l.firstPrizeAmount) FROM LotteryHistory l")
    Optional<Long> findTopPrize();

    Optional<LotteryHistory> findByRound(Integer round);

    List<LotteryHistory> findTop10ByOrderByRoundDesc(Pageable pageable);
}
