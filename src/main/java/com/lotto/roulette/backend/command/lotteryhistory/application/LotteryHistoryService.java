package com.lotto.roulette.backend.command.lotteryhistory.application;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistoryBulkSaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class LotteryHistoryService {

    private final LotteryHistoryBulkSaveRepository lotteryHistoryBulkSaveRepository;

    public void saveAll(MultipartFile excel) {
        lotteryHistoryBulkSaveRepository.saveAll(excel);
    }
}
