package com.lotto.roulette.backend.query.controller;

import com.lotto.roulette.backend.command.lotteryhistory.exception.LotteryHistoryException;
import com.lotto.roulette.backend.query.dto.LotteryHistoryResponse;
import com.lotto.roulette.backend.query.dto.TopPrizeResponse;
import com.lotto.roulette.backend.support.enviroment.DocumentationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LotteryHistoryQueryControllerTest extends DocumentationTest {

    @Test
    void 로또_역대_최고_상금액을_조회한다() throws Exception {
        // given
        given(lotteryHistoryQueryService.getTopPrize())
                .willReturn(new TopPrizeResponse("₩1,500,000,000"));

        // when
        ResultActions result = mockMvc.perform(get("/top-prize")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect((status().isOk()))
                .andDo(document("get-top-prize", HOST_INFO, DOCUMENT_RESPONSE,
                        responseFields(
                                fieldWithPath("topPrize").type(JsonFieldType.STRING).description("로또 역대 최고 상금액")
                        )));
    }

    @Test
    void 로또_당첨_회차로_조회한다() throws Exception {
        // given
        given(lotteryHistoryQueryService.getLotteryHistory(any()))
                .willReturn(new LotteryHistoryResponse(1, 2, 3,
                        4, 5, 6,
                        1000000000L, 3, 1));

        // when
        ResultActions result = mockMvc.perform(get("/lottery-history")
                .queryParam("round", "1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect((status().isOk()))
                .andDo(document("get-lottery-history", HOST_INFO, DOCUMENT_RESPONSE,
                        queryParameters(
                                parameterWithName("round").description("로또 회차")
                        ),
                        responseFields(
                                fieldWithPath("firstLotteryNumber").type(JsonFieldType.NUMBER).description("첫번째 로또 번호"),
                                fieldWithPath("secondLotteryNumber").type(JsonFieldType.NUMBER).description("두번째 로또 번호"),
                                fieldWithPath("thirdLotteryNumber").type(JsonFieldType.NUMBER).description("세번째 로또 번호"),
                                fieldWithPath("fourthLotteryNumber").type(JsonFieldType.NUMBER).description("네번째 로또 번호"),
                                fieldWithPath("fifthLotteryNumber").type(JsonFieldType.NUMBER).description("다섯번째 로또 번호"),
                                fieldWithPath("sixthLotteryNumber").type(JsonFieldType.NUMBER).description("여섯번째 로또 번호"),
                                fieldWithPath("firstPrizeAmount").type(JsonFieldType.NUMBER).description("일등 당첨 금액"),
                                fieldWithPath("winnerCount").type(JsonFieldType.NUMBER).description("당첨자 수"),
                                fieldWithPath("round").type(JsonFieldType.NUMBER).description("로또 회차")
                                )));
    }

    @Test
    void 로또_당첨_이력을_페이지_단위로_조회한다() throws Exception {
        // given
        given(lotteryHistoryQueryService.getLotteryHistories(any()))
                .willReturn(List.of(new LotteryHistoryResponse(1, 2, 3,
                        4, 5, 6,
                        1000000000L, 3, 1)));

        // when
        ResultActions result = mockMvc.perform(get("/lottery-histories")
                .queryParam("page", "1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect((status().isOk()))
                .andDo(document("get-lottery-history", HOST_INFO, DOCUMENT_RESPONSE,
                        queryParameters(
                                parameterWithName("page").description("페이지")
                        ),
                        responseFields(
                                fieldWithPath("[].firstLotteryNumber").type(JsonFieldType.NUMBER).description("첫번째 로또 번호"),
                                fieldWithPath("[].secondLotteryNumber").type(JsonFieldType.NUMBER).description("두번째 로또 번호"),
                                fieldWithPath("[].thirdLotteryNumber").type(JsonFieldType.NUMBER).description("세번째 로또 번호"),
                                fieldWithPath("[].fourthLotteryNumber").type(JsonFieldType.NUMBER).description("네번째 로또 번호"),
                                fieldWithPath("[].fifthLotteryNumber").type(JsonFieldType.NUMBER).description("다섯번째 로또 번호"),
                                fieldWithPath("[].sixthLotteryNumber").type(JsonFieldType.NUMBER).description("여섯번째 로또 번호"),
                                fieldWithPath("[].firstPrizeAmount").type(JsonFieldType.NUMBER).description("일등 당첨 금액"),
                                fieldWithPath("[].winnerCount").type(JsonFieldType.NUMBER).description("당첨자 수"),
                                fieldWithPath("[].round").type(JsonFieldType.NUMBER).description("로또 회차")
                        )));
    }

    @Test
    void 로또_당첨_이력_에러_코드() throws Exception {
        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/error-codes")
                .queryParam("className", "com.lotto.roulette.backend.command.lotteryhistory.exception.LotteryHistoryException")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect((status().isOk()))
                .andDo(document("lottery-history-error-code",
                        HOST_INFO,
                        DOCUMENT_RESPONSE,
                        responseFields(getErrorDescriptor(LotteryHistoryException.values()))
                ));
    }
}
