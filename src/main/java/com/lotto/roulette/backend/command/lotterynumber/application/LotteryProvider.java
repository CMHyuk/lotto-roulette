package com.lotto.roulette.backend.command.lotterynumber.application;

import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.lotterywinninghistory.LotteryWinningHistoryInfo;

public interface LotteryProvider {
    LotteryWinningHistoryInfo getLottoWinnerInfo(int drwNo);
}
