package com.lotto.roulette.backend.query.acceptance;

import com.lotto.roulette.backend.support.enviroment.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Sql("/fixture/lottery-history-fixture.sql")
public class LottoHistoryQueryAcceptanceTest extends AcceptanceTest {

    @Test
    void 로또_역대_최고_상금액을_조회한다() {
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/top-prize")
                .then().log().all()
                .extract();

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat((String) response.jsonPath().get("topPrize")).isEqualTo("₩1,500,000,000")
        );
    }
}
