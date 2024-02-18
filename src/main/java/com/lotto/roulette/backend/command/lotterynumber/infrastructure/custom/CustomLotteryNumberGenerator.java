package com.lotto.roulette.backend.command.lotterynumber.infrastructure.custom;

import com.lotto.roulette.backend.command.lotterynumber.application.LotteryNumberGenerator;
import com.lotto.roulette.backend.command.lotterynumber.dto.LottoNumberResponse;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lotto.roulette.backend.command.lotterynumber.infrastructure.LotteryGeneratorConst.CUSTOM;

@Component
public class CustomLotteryNumberGenerator implements LotteryNumberGenerator {

    private static final int LOTTERY_NUMBER_SIZE = 6;
    private static final int LAST_LOTTERY_NUMBER = 45;

    @Override
    public LottoNumberResponse generateLotteryNumbers() {
        Set<Integer> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTERY_NUMBER_SIZE) {
            int randomNumber = (int) (Math.random() * LAST_LOTTERY_NUMBER) + 1;
            lottoNumbers.add(randomNumber);
        }
        return new LottoNumberResponse(convertSetToString(lottoNumbers));
    }

    private static String convertSetToString(Set<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    @Override
    public String getGenerator() {
        return CUSTOM.getName();
    }
}
