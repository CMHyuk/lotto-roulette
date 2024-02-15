package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.infrastructure.chatgpt.ChatgptRequest;
import com.lotto.roulette.backend.command.infrastructure.chatgpt.LottoNumberResponse;

public interface LottoNumberGenerator {

    LottoNumberResponse getLottoNumbers(ChatgptRequest request);

    String getGenerator();
}
