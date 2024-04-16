package com.udemy.junit_lecture_1.testWithSpringBoot.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.junit_lecture_1.testWithSpringBoot.dto.BeerDto;

@RequestMapping("/api/v1/customers/{customerId}/")
@RestController
public class BeerOrderController {

    @GetMapping("orders")
    public String listOrders(@PathVariable("customerId") UUID customerId) {
        return customerId.toString();
    }

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@PathVariable("customerId") UUID customerId, @RequestBody BeerDto beerDto) {
        return customerId.toString() + ", " + beerDto.getBeerName();
    }

    @GetMapping("orders/{orderId}")
    public String getOrder(@PathVariable("customerId") UUID customerId, @PathVariable("orderId") UUID orderId) {
        return customerId.toString() + ", " + orderId.toString();
    }

    @PutMapping("/orders/{orderId}/pickup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickupOrder(@PathVariable("customerId") UUID customerId, @PathVariable("orderId") UUID orderId) {
    }
}
