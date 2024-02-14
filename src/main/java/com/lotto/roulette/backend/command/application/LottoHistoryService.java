package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.domain.LottoHistory;
import com.lotto.roulette.backend.command.domain.LottoHistoryRepository;
import com.lotto.roulette.backend.command.infrastructure.LottoWinnerInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LottoHistoryService {

    private final LottoHistoryRepository lottoHistoryRepository;

    public void save(LottoWinnerInfo lottoWinnerInfo) {
        LottoHistory lottoHistory = lottoWinnerInfo.toEntity();
        lottoHistoryRepository.save(lottoHistory);
    }
}
