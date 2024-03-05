package com.lotto.roulette.backend.query.controller;

import com.lotto.roulette.backend.query.dto.LotteryNumberFrequencyResponse;
import com.lotto.roulette.backend.support.enviroment.DocumentationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LotteryNumberFrequencyQueryControllerTest extends DocumentationTest {

    @Test
    void 로또_번호들의_빈도수를_조회한다() throws Exception {
        // given
        given(lotteryNumberFrequencyQueryService.findAll())
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

    @Test
    void 가장_많이_나온_로또_번호_6개를_조회한다() throws Exception {
        // given
        given(lotteryNumberFrequencyQueryService.findTop6MostFrequentNumbers())
                .willReturn(List.of(
                        LotteryNumberFrequencyResponse.of(6, 6),
                        LotteryNumberFrequencyResponse.of(5, 5),
                        LotteryNumberFrequencyResponse.of(4, 4),
                        LotteryNumberFrequencyResponse.of(3, 3),
                        LotteryNumberFrequencyResponse.of(2, 2),
                        LotteryNumberFrequencyResponse.of(1, 1)
                ));

        // when
        ResultActions result = mockMvc.perform(get("/lottery-number-frequency/top-six")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
                .andDo(document("get-lottery-number-frequency-top-six", HOST_INFO, DOCUMENT_RESPONSE,
                        responseFields(
                                fieldWithPath("[].lotteryNumber").type(JsonFieldType.NUMBER).description("로또 번호"),
                                fieldWithPath("[].frequency").type(JsonFieldType.NUMBER).description("빈도 수")
                        )));
    }
}
