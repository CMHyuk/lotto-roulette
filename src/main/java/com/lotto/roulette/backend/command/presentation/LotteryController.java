package com.lotto.roulette.backend.command.presentation;

import com.lotto.roulette.backend.command.application.LotteryFacade;
import com.lotto.roulette.backend.command.dto.LotteryWinningHistoryInfo;
import com.lotto.roulette.backend.command.infrastructure.chatgpt.ChatGptRequest;
import com.lotto.roulette.backend.command.infrastructure.chatgpt.LottoNumberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LotteryController {

    private final LotteryFacade lotteryFacade;

    @PostMapping("/lotto-winner-number")
    public ResponseEntity<LotteryWinningHistoryInfo> getLottoWinnerNumberInfo(@RequestParam int drwNo) {
        return ResponseEntity.ok(lotteryFacade.save(drwNo));
    }

    @PostMapping("/lotto-numbers/{generator}")
    public ResponseEntity<LottoNumberResponse> getLottoNumbers(@PathVariable String generator,
                                                               @RequestBody ChatGptRequest request) {
        return ResponseEntity.ok(lotteryFacade.getLottoNumbers(generator, request));
    }
}
