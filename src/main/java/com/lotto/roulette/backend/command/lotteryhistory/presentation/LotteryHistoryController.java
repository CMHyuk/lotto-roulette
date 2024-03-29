package com.lotto.roulette.backend.command.lotteryhistory.presentation;

import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryHistoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class LotteryHistoryController {

    private final LotteryHistoryFacade lotteryHistoryFacade;

    @PostMapping("/lottery-histories")
    public ResponseEntity<Void> save(@ModelAttribute MultipartFile lotteryHistoryExcel) {
        lotteryHistoryFacade.save(lotteryHistoryExcel);
        return ResponseEntity.ok().build();
    }
}
