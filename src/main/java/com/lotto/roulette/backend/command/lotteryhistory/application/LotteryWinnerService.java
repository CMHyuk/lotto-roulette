package com.lotto.roulette.backend.command.lotteryhistory.application;

import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.lotterywinninghistory.LotteryWinningHistoryInfo;
import com.lotto.roulette.backend.command.lotterynumber.application.LotteryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotteryWinnerService {

    private final LotteryProvider lotteryProvider;

    public LotteryWinningHistoryInfo getLottoWinnerInfo(int drwNo) {
        return lotteryProvider.getLottoWinnerInfo(drwNo);
    }
}
