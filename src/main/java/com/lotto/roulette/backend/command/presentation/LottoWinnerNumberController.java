package com.lotto.roulette.backend.command.presentation;

import com.lotto.roulette.backend.command.application.LottoFacade;
import com.lotto.roulette.backend.command.infrastructure.LottoWinnerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LottoWinnerNumberController {

    private final LottoFacade lottoFacade;

    @PostMapping("/lotto-winner-number")
    public ResponseEntity<LottoWinnerInfo> getLottoWinnerNumberInfo(@RequestParam int drwNo) {
        return ResponseEntity.ok(lottoFacade.save(drwNo));
    }
}
