package com.lotto.roulette.backend.command.lotteryhistory.domain;

import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.lotterywinninghistory.LotteryWinningHistoryInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LotteryHistoryService {

    private final LotteryHistoryRepository lotteryHistoryRepository;

    public void save(LotteryWinningHistoryInfo lotteryWinningHistoryInfo) {
        LotteryHistory lotteryHistory = lotteryWinningHistoryInfo.toEntity();
        lotteryHistoryRepository.save(lotteryHistory);
    }
}
