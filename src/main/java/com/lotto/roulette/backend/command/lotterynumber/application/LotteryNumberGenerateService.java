package com.lotto.roulette.backend.command.lotterynumber.application;

import com.lotto.roulette.backend.command.lotterynumber.dto.LottoNumberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotteryNumberGenerateService {

    private final LotteryNumberGeneratorFactory lotteryNumberGeneratorFactory;

    public LottoNumberResponse generateLottoNumbers(String generator) {
        LotteryNumberGenerator lotteryNumberGenerator = lotteryNumberGeneratorFactory.getGenerator(generator);
        return lotteryNumberGenerator.generateLotteryNumbers();
    }
}
