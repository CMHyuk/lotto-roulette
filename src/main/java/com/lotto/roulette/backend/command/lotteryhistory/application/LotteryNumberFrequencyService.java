package com.lotto.roulette.backend.command.lotteryhistory.application;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistoryRepository;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumberFrequencyBulkUpdateRepository;
import com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryNumbersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LotteryNumberFrequencyService {

    private final LotteryNumberFrequencyBulkUpdateRepository lotteryNumberFrequencyBulkUpdateRepository;
    private final LotteryHistoryRepository lotteryHistoryRepository;

    public void insertLotteryNumberFrequency() {
        List<LotteryHistoryNumbersDto> lotteryHistoryNumbers = lotteryHistoryRepository.findLotteryHistoryNumbers();
        List<Integer> lotteryNumbers = LotteryHistoryNumbersDto.toLotteryHistoryNumbers(lotteryHistoryNumbers);
        increaseLotteryNumberFrequency(lotteryNumbers);
    }

    @CacheEvict(value = "LotteryNumberFrequency", allEntries = true)
    public void increaseLotteryNumberFrequency(List<Integer> lotteryNumbers) {
        lotteryNumberFrequencyBulkUpdateRepository.updateAll(lotteryNumbers);
    }
}
