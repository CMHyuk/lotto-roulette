package com.lotto.roulette.backend.query.service;

import com.lotto.roulette.backend.common.exception.BusinessException;
import com.lotto.roulette.backend.query.dto.LotteryHistoryResponse;
import com.lotto.roulette.backend.query.dto.TopPrizeResponse;
import com.lotto.roulette.backend.query.repository.LotteryHistoryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static com.lotto.roulette.backend.command.lotteryhistory.exception.LotteryHistoryException.NOT_EXISTS_LOTTERY_HISTORY;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LotteryHistoryQueryService {

    private final LotteryHistoryQueryRepository lotteryHistoryQueryRepository;

    @Cacheable("topPrize")
    public TopPrizeResponse getTopPrize() {
        Long firstPrizeAmount = lotteryHistoryQueryRepository.findTopPrize()
                .orElseThrow(() -> BusinessException.from(NOT_EXISTS_LOTTERY_HISTORY));
        return new TopPrizeResponse(formatToKRW(firstPrizeAmount));
    }

    @Cacheable("lotteryHistory")
    public LotteryHistoryResponse getLotteryHistory(Integer round) {
        return lotteryHistoryQueryRepository.findByRound(round)
                .map(LotteryHistoryResponse::new)
                .orElseGet(LotteryHistoryResponse::createEmpty);
    }

    @Cacheable("lotteryHistory")
    public List<LotteryHistoryResponse> getLotteryHistories(Pageable pageable) {
        return lotteryHistoryQueryRepository.findTop10ByOrderByRoundDesc(pageable)
                .stream()
                .map(LotteryHistoryResponse::new)
                .toList();
    }

    private String formatToKRW(Long firstPrizeAmount) {
        NumberFormat koreanFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return koreanFormat.format(firstPrizeAmount);
    }
}
