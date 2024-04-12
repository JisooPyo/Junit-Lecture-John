package com.udemy.junit_lecture_1.testWithSpringBoot.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.udemy.junit_lecture_1.testWithSpringBoot.domain.Beer;
import com.udemy.junit_lecture_1.testWithSpringBoot.dto.BeerDto;
import com.udemy.junit_lecture_1.testWithSpringBoot.model.BeerStyleEnum;
import com.udemy.junit_lecture_1.testWithSpringBoot.repository.BeerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    @Override
    public List<BeerDto> listBeers(String beerName, BeerStyleEnum beerStyle) {

        List<Beer> beerList;

        if (!beerName.isEmpty() && !beerStyle.name().isEmpty()) {
            //search both
            beerList = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle);
        } else if (!beerName.isEmpty()) {
            //search beer name
            beerList = beerRepository.findAllByBeerName(beerName);
        } else if (!beerStyle.name().isEmpty()) {
            //search beer style
            beerList = beerRepository.findAllByBeerStyle(beerStyle);
        } else {
            beerList = beerRepository.findAll();
        }

        return beerList.stream().map(Beer::beerToBeerDto)
            .collect(Collectors.toList());
    }

    @Override
    public BeerDto findBeerById(UUID beerId) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(
            () -> new RuntimeException("Not Found")
        );

        return beer.beerToBeerDto();
    }
}
