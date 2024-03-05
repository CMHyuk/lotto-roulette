package com.lotto.roulette.backend.command;

import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryHistoryService;
import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryNumberFrequencyService;
import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.LotteryHistoryUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class LotteryInfoInitializer {

    private final LotteryHistoryService lotteryHistoryService;
    private final LotteryNumberFrequencyService lotteryNumberFrequencyService;

    @PostConstruct
    void insertLotteryHistories() {
        File excel = new File("src/main/resources/lotto.xlsx");
        MultipartFile lotteryHistoryExcel = LotteryHistoryUtils.convertFileToMultipartFile(excel);
        lotteryHistoryService.saveAll(lotteryHistoryExcel);
        lotteryNumberFrequencyService.insertLotteryNumberFrequency();
    }
}
