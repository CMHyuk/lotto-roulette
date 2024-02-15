package com.lotto.roulette.backend.command.infrastructure.lotto;

import com.lotto.roulette.backend.command.application.LotteryProvider;
import com.lotto.roulette.backend.command.dto.LotteryWinningHistoryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotteryWinningHistoryProvider implements LotteryProvider {

    private final LotteryWinningHistoryApi lotteryWinningHistoryApi;

    @Override
    public LotteryWinningHistoryInfo getLottoWinnerInfo(int drwNo) {
        String lottoWinnerInfo = lotteryWinningHistoryApi.getLottoWinnerInfo(drwNo);
        return LotteryFirstPrizeParser.parseLottoWinnerInfo(lottoWinnerInfo);
    }
}
