package com.lotto.roulette.backend.query.controller;

import com.lotto.roulette.backend.query.dto.LotteryNumberFrequencyResponse;
import com.lotto.roulette.backend.support.enviroment.DocumentationTest;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LotteryNumberFrequencyQueryControllerTest extends DocumentationTest {

    @Test
    void 로또_번호들의_빈도수를_조회한다() throws Exception {
        // given
        BDDMockito.given(lotteryNumberFrequencyQueryService.findAll())
                .willReturn(List.of(
                        LotteryNumberFrequencyResponse.of(1, 44),
                        LotteryNumberFrequencyResponse.of(35, 20)
                ));

        // when
        ResultActions result = mockMvc.perform(get("/lottery-number-frequency")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
                .andDo(document("get-lottery-number-frequency", HOST_INFO, DOCUMENT_RESPONSE,
                        responseFields(
                                fieldWithPath("[].lotteryNumber").type(JsonFieldType.NUMBER).description("로또 번호"),
                                fieldWithPath("[].frequency").type(JsonFieldType.NUMBER).description("빈도 수")
                        )));
    }
}
