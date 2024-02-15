package com.lotto.roulette.backend.command.infrastructure.chatgpt;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chatgpt", url = "https://api.openai.com/v1/completions")
public interface ChatGptApi {

    @PostMapping
    ResponseEntity<ChatGptResponse> getLottoNumbers(
            @RequestHeader("Authorization") String secretKey,
            @RequestBody ChatgptRequest chatgptRequest
    );
}
