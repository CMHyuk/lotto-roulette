package com.lotto.roulette.backend.query.service;

import com.lotto.roulette.backend.query.dto.LotteryNumberFrequencyResponse;
import com.lotto.roulette.backend.query.repository.LotteryNumberFrequencyQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lotto.roulette.backend.query.dto.LotteryNumberFrequencyResponse.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LotteryNumberFrequencyQueryService {

    private final LotteryNumberFrequencyQueryRepository lotteryNumberFrequencyQueryRepository;

    @Cacheable("LotteryNumberFrequency")
    public List<LotteryNumberFrequencyResponse> findAll() {
        return lotteryNumberFrequencyQueryRepository.findAll()
                .stream()
                .map(lotteryNumberFrequency -> of(lotteryNumberFrequency.getLotteryNumber(), lotteryNumberFrequency.getFrequency()))
                .toList();
    }

    @Cacheable("LotteryNumberTopSix")
    public List<LotteryNumberFrequencyResponse> findTopSixMostFrequentNumbers() {
        return lotteryNumberFrequencyQueryRepository.findTop6MostFrequentNumbers()
                .stream()
                .map(lotteryNumberFrequency -> of(lotteryNumberFrequency.getLotteryNumber(), lotteryNumberFrequency.getFrequency()))
                .toList();
    }
}
