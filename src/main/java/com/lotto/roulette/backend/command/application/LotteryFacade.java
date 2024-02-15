package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.dto.LotteryWinningHistoryInfo;
import com.lotto.roulette.backend.command.infrastructure.chatgpt.ChatGptRequest;
import com.lotto.roulette.backend.command.infrastructure.chatgpt.LottoNumberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotteryFacade {

    private final LotteryWinnerService lotteryWinnerService;
    private final LotteryHistoryService lotteryHistoryService;
    private final LotteryNumberGeneratorFactory lotteryNumberGeneratorFactory;

    public LotteryWinningHistoryInfo save(int drwNO) {
        LotteryWinningHistoryInfo lotteryWinningHistoryInfo = lotteryWinnerService.getLottoWinnerInfo(drwNO);
        lotteryHistoryService.save(lotteryWinningHistoryInfo);
        return lotteryWinningHistoryInfo;
    }

    public LottoNumberResponse getLottoNumbers(String generator, ChatGptRequest request) {
        LotteryNumberGenerator lotteryNumberGenerator = lotteryNumberGeneratorFactory.getGenerator(generator);
        return lotteryNumberGenerator.getLottoNumbers(request);
    }
}
