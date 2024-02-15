package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.infrastructure.chatgpt.ChatGptRequest;
import com.lotto.roulette.backend.command.infrastructure.chatgpt.LottoNumberResponse;

public interface LotteryNumberGenerator {

    LottoNumberResponse getLottoNumbers(ChatGptRequest request);

    String getGenerator();
}
