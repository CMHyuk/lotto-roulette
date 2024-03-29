package com.lotto.roulette.backend.command.lotteryhistory.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class LotteryHistoryFacade {

    private final LotteryHistoryService lotteryHistoryService;
    private final LotteryNumberFrequencyService lotteryNumberFrequencyService;

    public void save(MultipartFile lotteryHistoryExcel) {
        lotteryHistoryService.saveAll(lotteryHistoryExcel);
        lotteryNumberFrequencyService.insertLotteryNumberFrequency();
    }
}
