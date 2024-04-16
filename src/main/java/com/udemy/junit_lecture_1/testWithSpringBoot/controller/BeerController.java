package com.udemy.junit_lecture_1.testWithSpringBoot.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.junit_lecture_1.testWithSpringBoot.dto.BeerDto;
import com.udemy.junit_lecture_1.testWithSpringBoot.model.BeerStyleEnum;
import com.udemy.junit_lecture_1.testWithSpringBoot.service.BeerService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/beer")
@RestController
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public ResponseEntity<List<BeerDto>> listBeers(
        @RequestParam(value = "beerName", required = false) String beerName,
        @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle) {

        List<BeerDto> beerList = beerService.listBeers(beerName, beerStyle);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @GetMapping(path = {"/{beerId}"})
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.findBeerById(beerId), HttpStatus.OK);
    }
}
