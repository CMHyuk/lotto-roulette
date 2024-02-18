package com.lotto.roulette.backend.command.lotteryfrequency.application;

import com.lotto.roulette.backend.command.lotteryfrequency.domain.LotteryNumberFrequency;
import com.lotto.roulette.backend.command.lotteryfrequency.domain.LotteryNumberFrequencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LotteryNumberFrequencyService {

    private final LotteryNumberFrequencyRepository lotteryNumberFrequencyRepository;

    public void save(List<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            LotteryNumberFrequency frequency = lotteryNumberFrequencyRepository.findByLotteryNumber(winningNumber)
                    .orElseGet(() -> {
                        LotteryNumberFrequency lotteryNumberFrequency = new LotteryNumberFrequency(winningNumber);
                        lotteryNumberFrequencyRepository.save(lotteryNumberFrequency);
                        return lotteryNumberFrequency;
                    });
            frequency.increaseFrequency();
        }
    }
}
