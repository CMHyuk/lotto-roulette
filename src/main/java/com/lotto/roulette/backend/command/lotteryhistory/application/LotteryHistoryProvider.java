package com.lotto.roulette.backend.command.lotteryhistory.application;

import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.lotteryapi.LotteryHistoryApiResponse;

public interface LotteryHistoryProvider {
    LotteryHistoryApiResponse getLotteryHistory(int drwNo);
}
