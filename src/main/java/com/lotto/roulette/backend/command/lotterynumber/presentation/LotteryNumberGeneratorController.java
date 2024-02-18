package com.lotto.roulette.backend.command.lotterynumber.presentation;

import com.lotto.roulette.backend.command.lotterynumber.dto.LottoNumberResponse;
import com.lotto.roulette.backend.command.lotterynumber.application.LotteryNumberGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LotteryNumberGeneratorController {

    private final LotteryNumberGenerateService lotteryNumberGenerateService;

    @PostMapping("/lotto-numbers/{generator}")
    public ResponseEntity<LottoNumberResponse> getLottoNumbers(@PathVariable String generator) {
        return ResponseEntity.ok(lotteryNumberGenerateService.generateLottoNumbers(generator));
    }
}
