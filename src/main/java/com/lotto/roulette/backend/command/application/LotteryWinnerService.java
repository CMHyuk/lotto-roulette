package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.dto.LotteryWinningHistoryInfo;
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
