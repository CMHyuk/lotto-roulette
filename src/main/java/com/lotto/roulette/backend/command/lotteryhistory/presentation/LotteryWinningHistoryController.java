package com.lotto.roulette.backend.command.lotteryhistory.presentation;

import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryWinnerService;
import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.LotteryWinningHistoryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LotteryWinningHistoryController {

    private final LotteryWinnerService lotteryWinnerService;

    @PostMapping("/lotto-winner-number")
    public ResponseEntity<LotteryWinningHistoryInfo> getLottoWinnerNumberInfo(@RequestParam int drwNo) {
        return ResponseEntity.ok(lotteryWinnerService.getLottoWinnerInfo(drwNo));
    }
}
