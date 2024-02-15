package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.domain.LotteryHistory;
import com.lotto.roulette.backend.command.domain.LotteryHistoryRepository;
import com.lotto.roulette.backend.command.dto.LotteryWinningHistoryInfo;
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
