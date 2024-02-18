package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.lotto.roulette.backend.command.lotterynumber.application.LotteryProvider;
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
