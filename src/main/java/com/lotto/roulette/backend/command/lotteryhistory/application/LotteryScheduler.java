package com.lotto.roulette.backend.command.lotteryhistory.application;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistoryRepository;
import com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryApiResponse;
import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.LotteryHistoryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LotteryScheduler {

    private final LotteryHistoryRepository lotteryHistoryRepository;
    private final LotteryHistoryProvider lotteryHistoryProvider;
    private final LotteryNumberFrequencyService lotteryNumberFrequencyService;
    private final LotteryHistoryService lotteryHistoryService;

    @Scheduled(cron = "15 45 20 * * SAT")
    public void increaseLotteryNumberFrequency() {
        Integer drwNo = lotteryHistoryRepository.findLatestRound();
        LotteryHistoryApiResponse lotteryHistory = lotteryHistoryProvider.getLotteryHistory(drwNo);
        List<Integer> lotteryNumbers = lotteryHistory.getLotteryNumbers();
        lotteryNumberFrequencyService.increaseLotteryNumberFrequency(lotteryNumbers);
    }

    @Scheduled(cron = "0 45 20 * * SAT")
    public void saveLotteryHistory() {
        Integer drwNo = lotteryHistoryRepository.findLatestRound();
        LotteryHistoryApiResponse response = lotteryHistoryProvider.getLotteryHistory(drwNo + 1);
        LotteryHistory lotteryHistory = LotteryHistoryInfo.toEntity(response.toLotteryHistoryInfo());
        lotteryHistoryService.save(lotteryHistory);
    }

}
