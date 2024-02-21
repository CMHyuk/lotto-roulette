package com.lotto.roulette.backend.command.lotteryhistory.application;

import com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryApiResponse;

public interface LotteryHistoryProvider {
    LotteryHistoryApiResponse getLotteryHistory(int drwNo);
}
