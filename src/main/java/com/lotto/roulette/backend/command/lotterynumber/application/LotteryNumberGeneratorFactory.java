package com.lotto.roulette.backend.command.lotterynumber.application;

import com.lotto.roulette.backend.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.lotto.roulette.backend.command.exception.LotteryException.NOT_EXISTS_PROVIDER;
import static java.util.stream.Collectors.toMap;

@Component
public class LotteryNumberGeneratorFactory {

    private final Map<String, LotteryNumberGenerator> lottoNumberGenerators;

    public LotteryNumberGeneratorFactory(List<LotteryNumberGenerator> lotteryNumberGenerators) {
        this.lottoNumberGenerators = lotteryNumberGenerators.stream()
                .collect(toMap(LotteryNumberGenerator::getGenerator, generator -> generator));
    }

    public LotteryNumberGenerator getGenerator(String generator) {
        return Optional.ofNullable(lottoNumberGenerators.get(generator))
                .orElseThrow(() -> BusinessException.from(NOT_EXISTS_PROVIDER));
    }
}
