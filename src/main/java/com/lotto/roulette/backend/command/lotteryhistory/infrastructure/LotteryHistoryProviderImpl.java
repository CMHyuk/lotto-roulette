package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryHistoryProvider;
import com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotteryHistoryProviderImpl implements LotteryHistoryProvider {

    private final LotteryHistoryApi lotteryHistoryApi;

    @Override
    public LotteryHistoryApiResponse getLotteryHistory(int drwNo) {
        String response = lotteryHistoryApi.getLotteryHistoryInfo(drwNo);
        return LotteryHistoryUtils.parseLotteryHistoryApi(response);
    }
}
