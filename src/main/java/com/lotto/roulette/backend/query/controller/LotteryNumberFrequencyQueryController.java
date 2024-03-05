package com.lotto.roulette.backend.query.controller;

import com.lotto.roulette.backend.query.dto.LotteryNumberFrequencyResponse;
import com.lotto.roulette.backend.query.service.LotteryNumberFrequencyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lottery-number-frequency")
@RequiredArgsConstructor
public class LotteryNumberFrequencyQueryController {

    private final LotteryNumberFrequencyQueryService lotteryNumberFrequencyQueryService;

    @GetMapping
    public ResponseEntity<List<LotteryNumberFrequencyResponse>> findAll() {
        return ResponseEntity.ok(lotteryNumberFrequencyQueryService.findAll());
    }

    @GetMapping("/top-six")
    public ResponseEntity<List<LotteryNumberFrequencyResponse>> findTop6MostFrequentNumbers() {
        return ResponseEntity.ok(lotteryNumberFrequencyQueryService.findTop6MostFrequentNumbers());
    }
}
