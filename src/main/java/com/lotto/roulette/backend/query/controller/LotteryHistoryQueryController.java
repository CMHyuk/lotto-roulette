package com.lotto.roulette.backend.query.controller;

import com.lotto.roulette.backend.query.dto.TopPrizeResponse;
import com.lotto.roulette.backend.query.service.LotteryHistoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LotteryHistoryQueryController {

    private final LotteryHistoryQueryService lotteryHistoryQueryService;

    @GetMapping("/top-prize")
    public TopPrizeResponse getTopPrize() {
        return lotteryHistoryQueryService.getTopPrize();
    }
}
