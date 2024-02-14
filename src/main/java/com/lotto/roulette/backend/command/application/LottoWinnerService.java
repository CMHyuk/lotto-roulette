package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.dto.LottoWinnerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LottoWinnerService {

    private final LottoWinnerNumberInfoProvider lottoWinnerNumberInfoProvider;

    public LottoWinnerInfo getLottoWinnerInfo(int drwNo) {
        return lottoWinnerNumberInfoProvider.getLottoWinnerInfo(drwNo);
    }
}
