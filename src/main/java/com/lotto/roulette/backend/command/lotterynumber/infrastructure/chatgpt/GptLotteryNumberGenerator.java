package com.lotto.roulette.backend.command.lotterynumber.infrastructure.chatgpt;

import com.lotto.roulette.backend.command.lotterynumber.application.LotteryNumberGenerator;
import com.lotto.roulette.backend.command.lotterynumber.dto.LottoNumberResponse;
import com.lotto.roulette.backend.common.ApiResponseExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.lotto.roulette.backend.command.lotterynumber.infrastructure.LotteryGeneratorConst.CHATGPT;

@Component
@RequiredArgsConstructor
public class GptLotteryNumberGenerator implements LotteryNumberGenerator {

    private static final String BEARER = "Bearer ";
    private static final String MODEL = "gpt-3.5-turbo-instruct";
    private static final int TEMPERATURE = 1;

    @Value("${chatgpt.prompt}")
    private String prompt;

    @Value("${chatgpt.api-key}")
    private String secretKey;

    private final ChatGptApi chatGPTApi;

    @Override
    public LottoNumberResponse generateLotteryNumbers() {
        String bearerToken = BEARER + secretKey;
        ChatGptRequest chatGptRequest = ChatGptRequest.of(MODEL, prompt, TEMPERATURE);
        ResponseEntity<ChatGptResponse> response = chatGPTApi.getLottoNumbers(bearerToken, chatGptRequest);
        ChatGptResponse chatGptResponse = ApiResponseExtractor.getBody(response);
        return LottoNumberResponse.from(chatGptResponse);
    }

    @Override
    public String getGenerator() {
        return CHATGPT.getName();
    }
}
