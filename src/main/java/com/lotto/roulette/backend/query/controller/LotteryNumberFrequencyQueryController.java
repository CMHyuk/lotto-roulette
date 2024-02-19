package com.lotto.roulette.backend.query.controller;

import com.lotto.roulette.backend.query.dto.LotteryNumberFrequencyResponse;
import com.lotto.roulette.backend.query.service.LotteryNumberFrequencyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LotteryNumberFrequencyQueryController {

    private final LotteryNumberFrequencyQueryService lotteryNumberFrequencyQueryService;

    @GetMapping("/lottery-number-frequency")
    public ResponseEntity<List<LotteryNumberFrequencyResponse>> findAll() {
        return ResponseEntity.ok(lotteryNumberFrequencyQueryService.findAll());
    }
}
