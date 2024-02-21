package com.lotto.roulette.backend.command.lotteryhistory.application;

import com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LotteryHistoryFacade {

    private final LotteryHistoryProvider lotteryHistoryProvider;
    private final LotteryNumberFrequencyService lotteryNumberFrequencyService;

    public void increaseLotteryNumberFrequency(int drwNo) {
        LotteryHistoryApiResponse lotteryHistory = lotteryHistoryProvider.getLotteryHistory(drwNo);
        List<Integer> lotteryNumbers = lotteryHistory.getLotteryNumbers();
        lotteryNumberFrequencyService.increaseLotteryNumberFrequency(lotteryNumbers);
    }
}
