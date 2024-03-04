package com.lotto.roulette.backend.query.controller;

import com.lotto.roulette.backend.query.dto.LotteryHistoryResponse;
import com.lotto.roulette.backend.query.dto.TopPrizeResponse;
import com.lotto.roulette.backend.query.service.LotteryHistoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LotteryHistoryQueryController {

    private final LotteryHistoryQueryService lotteryHistoryQueryService;

    @GetMapping("/top-prize")
    public ResponseEntity<TopPrizeResponse> getTopPrize() {
        return ResponseEntity.ok(lotteryHistoryQueryService.getTopPrize());
    }

    @GetMapping("/lottery-history")
    public ResponseEntity<LotteryHistoryResponse> getLotteryHistory(@RequestParam Integer round) {
        return ResponseEntity.ok(lotteryHistoryQueryService.getLotteryHistory(round));
    }

    @GetMapping("/lottery-histories")
    public ResponseEntity<List<LotteryHistoryResponse>> getLotteryHistories(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(lotteryHistoryQueryService.getLotteryHistories(pageable));
    }
}
