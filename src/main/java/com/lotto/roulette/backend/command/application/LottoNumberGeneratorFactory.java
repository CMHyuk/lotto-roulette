package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.lotto.roulette.backend.command.exception.LottoException.NOT_EXISTS_PROVIDER;
import static java.util.stream.Collectors.toMap;

@Component
public class LottoNumberGeneratorFactory {

    private final Map<String, LottoNumberGenerator> lottoNumberGenerators;

    public LottoNumberGeneratorFactory(List<LottoNumberGenerator> lottoNumberGenerators) {
        this.lottoNumberGenerators = lottoNumberGenerators.stream()
                .collect(toMap(LottoNumberGenerator::getGenerator, generator -> generator));
    }

    public LottoNumberGenerator getGenerator(String generator) {
        return Optional.ofNullable(lottoNumberGenerators.get(generator))
                .orElseThrow(() -> BusinessException.from(NOT_EXISTS_PROVIDER));
    }
}
