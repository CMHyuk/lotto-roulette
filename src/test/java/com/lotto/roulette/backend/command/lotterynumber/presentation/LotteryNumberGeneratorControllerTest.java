package com.lotto.roulette.backend.command.lotterynumber.presentation;

import com.lotto.roulette.backend.command.lotterynumber.dto.LottoNumberResponse;
import com.lotto.roulette.backend.command.lotterynumber.exception.LotteryNumberGeneratorException;
import com.lotto.roulette.backend.support.enviroment.DocumentationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LotteryNumberGeneratorControllerTest extends DocumentationTest {

    @Test
    void 로또_번호를_생성한다() throws Exception {
        // given
        given(lotteryNumberGenerateService.generateLottoNumbers("chatgpt"))
                .willReturn(new LottoNumberResponse("1 2 3 4 5 6"));

        // when
        ResultActions result = mockMvc.perform(post("/lotto-numbers/{generator}", "chatgpt")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andDo(document("generate-lottery-numbers", HOST_INFO, DOCUMENT_RESPONSE,
                responseFields(
                        fieldWithPath("result").type(JsonFieldType.STRING).description("1 2 3 4 5 6")
                )));
    }

    @Test
    void 로또_번호_에러_코드() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/error-codes")
                .queryParam("className", "com.lotto.roulette.backend.command.lotterynumber.exception.LotteryNumberGeneratorException")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect((status().isOk()))
                .andDo(document("lottery-number-error-code",
                        HOST_INFO,
                        DOCUMENT_RESPONSE,
                        responseFields(getErrorDescriptor(LotteryNumberGeneratorException.values()))
                ));
    }
}
