package com.lotto.roulette.backend.command.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "lottoApi", url = "https://www.dhlottery.co.kr")
public interface LottoWinnerApi {

    @GetMapping("/common.do?method=getLottoNumber")
    String getLottoWinnerInfo(@RequestParam int drwNo);
}
