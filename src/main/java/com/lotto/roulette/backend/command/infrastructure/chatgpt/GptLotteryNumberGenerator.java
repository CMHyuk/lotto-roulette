package com.lotto.roulette.backend.command.infrastructure.chatgpt;

import com.lotto.roulette.backend.command.application.LotteryNumberGenerator;
import com.lotto.roulette.backend.common.ApiResponseExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.lotto.roulette.backend.command.infrastructure.LotteryConst.CHATGPT;

@Component
@RequiredArgsConstructor
public class GptLotteryNumberGenerator implements LotteryNumberGenerator {

    private static final String BEARER = "Bearer ";

    @Value("${chatgpt.api-key}")
    private String secretKey;

    private final ChatGptApi chatGPTApi;

    @Override
    public LottoNumberResponse getLottoNumbers(ChatGptRequest chatgptRequest) {
        String bearerToken = BEARER + secretKey;
        ResponseEntity<ChatGptResponse> response = chatGPTApi.getLottoNumbers(bearerToken, chatgptRequest);
        ChatGptResponse chatGptResponse = ApiResponseExtractor.getBody(response);
        return LottoNumberResponse.from(chatGptResponse);
    }

    @Override
    public String getGenerator() {
        return CHATGPT.getName();
    }
}
