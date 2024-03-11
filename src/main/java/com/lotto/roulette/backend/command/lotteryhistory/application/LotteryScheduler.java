package com.lotto.roulette.backend.command.lotteryhistory.application;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistoryRepository;
import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.lotteryapi.LotteryHistoryApiResponse;
import com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LotteryScheduler {

    private static final String CRON_EXPRESSION = "0 45 20 * * SAT";

    private final LotteryHistoryRepository lotteryHistoryRepository;
    private final LotteryHistoryProvider lotteryHistoryProvider;
    private final LotteryNumberFrequencyService lotteryNumberFrequencyService;
    private final LotteryHistoryService lotteryHistoryService;

    @Scheduled(cron = CRON_EXPRESSION)
    public void saveLotteryHistoryAndIncreaseFrequency() {
        Integer drwNo = lotteryHistoryRepository.findLatestRound();
        LotteryHistoryApiResponse response = lotteryHistoryProvider.getLotteryHistory(drwNo + 1);
        LotteryHistory lotteryHistory = LotteryHistoryInfo.toEntity(response.toLotteryHistoryInfo());
        lotteryHistoryService.save(lotteryHistory);
        List<Integer> lotteryNumbers = response.getLotteryNumbers();
        lotteryNumberFrequencyService.increaseLotteryNumberFrequency(lotteryNumbers);
    }
}
