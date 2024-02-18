package com.lotto.roulette.backend.command.lotterynumber.application;

import com.lotto.roulette.backend.command.lotterynumber.dto.LottoNumberResponse;

public interface LotteryNumberGenerator {

    LottoNumberResponse generateLotteryNumbers();

    String getGenerator();
}
