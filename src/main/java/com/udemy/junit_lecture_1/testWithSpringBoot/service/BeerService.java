package com.udemy.junit_lecture_1.testWithSpringBoot.service;

import java.util.List;
import java.util.UUID;

import com.udemy.junit_lecture_1.testWithSpringBoot.dto.BeerDto;
import com.udemy.junit_lecture_1.testWithSpringBoot.model.BeerStyleEnum;

public interface BeerService {
    List<BeerDto> listBeers(String beerName, BeerStyleEnum beerStyle);

    BeerDto findBeerById(UUID beerId);
}
