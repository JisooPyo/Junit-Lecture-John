package com.udemy.junit_lecture_1.testWithSpringBoot.controller;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import com.udemy.junit_lecture_1.testWithSpringBoot.dto.BeerDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BeerOrderControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String CUSTOMER_ID = "a417b2f8-8ddf-488d-afb4-aa561f207842";
    private final String COMMON_URI = "/api/v1/customers/" + CUSTOMER_ID + "/";

    @Test
    void listOrders() {
        String result =
            restTemplate.getForObject(
                COMMON_URI + "orders",
                String.class
            );

        assertThat(result).isEqualTo(CUSTOMER_ID);
    }

    @Test
    void placeOrder() {
        String beerName = "맛있는 맥주";
        BeerDto beerDto = BeerDto.builder()
            .beerName(beerName)
            .build();
        String result =
            restTemplate.postForObject(
                COMMON_URI + "orders"
                , beerDto
                , String.class
            );

        assertThat(result).isEqualTo(CUSTOMER_ID + ", " + beerName);
    }

    @Test
    void getOrder() {
        String orderId = "a4777b8c-6fff-4110-a576-566ca36d9158";
        String result =
            restTemplate.getForObject(
                COMMON_URI + "orders/" + orderId,
                String.class
            );

        assertThat(result).isEqualTo(CUSTOMER_ID + ", " + orderId);
    }

    @Test
    void pickupOrder() {
        String orderId = "a4777b8c-6fff-4110-a576-566ca36d9158";
        restTemplate.put(COMMON_URI + "/orders/" + orderId + "/pickup", HttpStatus.NO_CONTENT);
    }
}
