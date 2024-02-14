package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.infrastructure.LottoWinnerInfo;

public interface LottoWinnerNumberInfoProvider {
    LottoWinnerInfo getLottoWinnerInfo(int drwNo);
}
