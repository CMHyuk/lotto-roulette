package com.lotto.roulette.backend.command.lotteryhistory.domain;

import org.springframework.web.multipart.MultipartFile;


public interface LotteryHistoryBulkSaveRepository {
    void saveAll(MultipartFile excel);
}
