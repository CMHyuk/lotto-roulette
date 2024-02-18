package com.lotto.roulette.backend.query.service;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.query.repository.LotteryHistoryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LotteryHistoryQueryService {

    private final LotteryHistoryQueryRepository lotteryHistoryQueryRepository;

    public Long getTopPrize() {
        LotteryHistory lotteryHistory = lotteryHistoryQueryRepository.findTopByOrderByFirstPrizeAmountDesc()
                .orElseThrow();

        return lotteryHistory.getFirstPrizeAmount();
    }
}
