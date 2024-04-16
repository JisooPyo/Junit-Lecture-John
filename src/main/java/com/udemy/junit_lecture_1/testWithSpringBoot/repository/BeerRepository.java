package com.udemy.junit_lecture_1.testWithSpringBoot.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.junit_lecture_1.testWithSpringBoot.domain.Beer;
import com.udemy.junit_lecture_1.testWithSpringBoot.model.BeerStyleEnum;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    List<Beer> findAllByBeerName(String beerName);

    List<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle);

    List<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle);
}
