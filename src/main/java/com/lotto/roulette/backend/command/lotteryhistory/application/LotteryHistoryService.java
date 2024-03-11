package com.lotto.roulette.backend.command.lotteryhistory.application;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistoryBulkSaveRepository;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class LotteryHistoryService {

    private final LotteryHistoryBulkSaveRepository lotteryHistoryBulkSaveRepository;
    private final LotteryHistoryRepository lotteryHistoryRepository;

    public void saveAll(MultipartFile lotteryHistoryExcel) {
        lotteryHistoryBulkSaveRepository.saveAll(lotteryHistoryExcel);
    }

    @CacheEvict(value = "lotteryHistory", allEntries = true)
    public void save(LotteryHistory lotteryHistory) {
        lotteryHistoryRepository.save(lotteryHistory);
    }
}
