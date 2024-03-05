package com.lotto.roulette.backend.command.lotteryhistory.domain;

import com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryNumbersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotteryHistoryRepository extends JpaRepository<LotteryHistory, Long> {
    @Query("SELECT round FROM LotteryHistory ORDER BY round DESC LIMIT 1")
    Integer findLatestRound();

    @Query("select " +
            "new com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryNumbersDto(" +
            "lh.lotteryNumber.firstLotteryNumber, lh.lotteryNumber.secondLotteryNumber, lh.lotteryNumber.thirdLotteryNumber, " +
            "lh.lotteryNumber.fourthLotteryNumber, lh.lotteryNumber.fifthLotteryNumber, lh.lotteryNumber.sixthLotteryNumber) " +
            "from LotteryHistory lh")
    List<LotteryHistoryNumbersDto> findLotteryHistoryNumbers();
}
