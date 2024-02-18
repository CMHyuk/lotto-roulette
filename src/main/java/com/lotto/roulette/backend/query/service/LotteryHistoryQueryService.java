package com.lotto.roulette.backend.query.service;

import com.lotto.roulette.backend.common.exception.BusinessException;
import com.lotto.roulette.backend.query.dto.TopPrizeResponse;
import com.lotto.roulette.backend.query.repository.LotteryHistoryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.lotto.roulette.backend.command.lotteryhistory.exception.LotteryHistoryException.NOT_EXISTS_LOTTERY_HISTORY;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LotteryHistoryQueryService {

    private final LotteryHistoryQueryRepository lotteryHistoryQueryRepository;

    public TopPrizeResponse getTopPrize() {
        Long firstPrizeAmount = lotteryHistoryQueryRepository.findTopPrize()
                .orElseThrow(() -> BusinessException.from(NOT_EXISTS_LOTTERY_HISTORY));
        return new TopPrizeResponse(firstPrizeAmount);
    }
}
