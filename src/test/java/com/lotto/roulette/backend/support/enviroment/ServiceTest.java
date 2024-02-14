package com.lotto.roulette.backend.support.enviroment;

import com.lotto.roulette.backend.support.isolation.DatabaseIsolation;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DatabaseIsolation
public class ServiceTest {
}
