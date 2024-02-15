package com.lotto.roulette.backend.command.infrastructure.lotto;

import com.lotto.roulette.backend.command.application.LottoWinnerNumberInfoProvider;
import com.lotto.roulette.backend.command.dto.LottoWinnerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LottoWinnerProvider implements LottoWinnerNumberInfoProvider {

    private final LottoWinnerApi lottoWinnerApi;

    @Override
    public LottoWinnerInfo getLottoWinnerInfo(int drwNo) {
        String lottoWinnerInfo = lottoWinnerApi.getLottoWinnerInfo(drwNo);
        return LottoWinnerParser.parseLottoWinnerInfo(lottoWinnerInfo);
    }
}
