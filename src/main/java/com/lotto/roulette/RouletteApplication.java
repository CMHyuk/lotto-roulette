package com.lotto.roulette;

import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryHistoryService;
import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.LotteryHistoryUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@EnableCaching
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@RequiredArgsConstructor
public class RouletteApplication {

    private final LotteryHistoryService lotteryHistoryService;

    public static void main(String[] args) {
        SpringApplication.run(RouletteApplication.class, args);
    }

    @PostConstruct
    void insertLotteryHistories() {
        File excel = new File("src/main/resources/lotto.xlsx");
        MultipartFile lotteryHistoryExcel = LotteryHistoryUtils.convertFileToMultipartFile(excel);
        lotteryHistoryService.saveAll(lotteryHistoryExcel);
    }
}
