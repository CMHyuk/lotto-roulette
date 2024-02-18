package com.lotto.roulette.backend.query.controller;

import com.lotto.roulette.backend.query.dto.TopPrizeResponse;
import com.lotto.roulette.backend.support.enviroment.DocumentationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LotteryHistoryQueryControllerTest extends DocumentationTest {

    @Test
    void 로또_역대_최고_상금액을_조회한다() throws Exception {
        // given
        given(lotteryHistoryQueryService.getTopPrize())
                .willReturn(new TopPrizeResponse(1500000000L));

        // when
        ResultActions result = mockMvc.perform(get("/top-prize")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect((status().isOk()))
                .andDo(document("get-top-prize", HOST_INFO, DOCUMENT_RESPONSE,
                        responseFields(
                                fieldWithPath("topPrize").type(JsonFieldType.NUMBER).description("로또 역대 최고 상금액")
                        )));
    }
}
