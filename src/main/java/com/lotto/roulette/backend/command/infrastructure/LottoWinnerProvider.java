package com.lotto.roulette.backend.command.infrastructure;

import com.lotto.roulette.backend.command.application.LottoWinnerNumberInfoProvider;
import com.lotto.roulette.backend.command.dto.LottoWinnerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LottoWinnerProvider implements LottoWinnerNumberInfoProvider {

    private final LottoWinnerNumberApi lottoWinnerNumberApi;

    @Override
    public LottoWinnerInfo getLottoWinnerInfo(int drwNo) {
        String lottoWinnerInfo = lottoWinnerNumberApi.getLottoWinnerInfo(drwNo);
        return LottoWinnerParser.parseLottoWinnerInfo(lottoWinnerInfo);
    }
}
