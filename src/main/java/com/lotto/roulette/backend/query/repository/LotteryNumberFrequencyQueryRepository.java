package com.lotto.roulette.backend.query.repository;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumberFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotteryNumberFrequencyQueryRepository extends JpaRepository<LotteryNumberFrequency, Long> {

    @Query("select lnf from LotteryNumberFrequency lnf order by lnf.frequency desc limit 6")
    List<LotteryNumberFrequency> findTop6MostFrequentNumbers();
}
