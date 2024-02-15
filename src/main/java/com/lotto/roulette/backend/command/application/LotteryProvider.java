package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.dto.LotteryWinningHistoryInfo;

public interface LotteryProvider {
    LotteryWinningHistoryInfo getLottoWinnerInfo(int drwNo);
}
