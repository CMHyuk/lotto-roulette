package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.dto.LottoWinnerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LottoFacade {

    private final LottoWinnerService lottoWinnerService;
    private final LottoHistoryService lottoHistoryService;

    public LottoWinnerInfo save(int drwNO) {
        LottoWinnerInfo lottoWinnerInfo = lottoWinnerService.getLottoWinnerInfo(drwNO);
        lottoHistoryService.save(lottoWinnerInfo);
        return lottoWinnerInfo;
    }
}
