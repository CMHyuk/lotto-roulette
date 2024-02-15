package com.lotto.roulette.backend.command.presentation;

import com.lotto.roulette.backend.command.application.LottoFacade;
import com.lotto.roulette.backend.command.application.LottoNumberGenerator;
import com.lotto.roulette.backend.command.dto.LottoWinnerInfo;
import com.lotto.roulette.backend.command.infrastructure.chatgpt.ChatgptRequest;
import com.lotto.roulette.backend.command.infrastructure.chatgpt.ChatGptResponse;
import com.lotto.roulette.backend.command.infrastructure.chatgpt.LottoNumberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LottoController {

    private final LottoFacade lottoFacade;
    private final LottoNumberGenerator lottoNumberGenerator;

    @PostMapping("/lotto-winner-number")
    public ResponseEntity<LottoWinnerInfo> getLottoWinnerNumberInfo(@RequestParam int drwNo) {
        return ResponseEntity.ok(lottoFacade.save(drwNo));
    }

    @PostMapping("/lotto-numbers")
    public ResponseEntity<LottoNumberResponse> getLottoNumbers(@RequestBody ChatgptRequest request) {
        return ResponseEntity.ok(lottoNumberGenerator.getLottoNumbers(request));
    }
}
