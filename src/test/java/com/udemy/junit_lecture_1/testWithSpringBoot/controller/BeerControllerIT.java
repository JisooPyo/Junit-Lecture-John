package com.udemy.junit_lecture_1.testWithSpringBoot.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testListBeers() {
        List list = restTemplate.getForObject("/api/v1/beer", List.class);

        assertThat(list).hasSize(3);
    }
}
