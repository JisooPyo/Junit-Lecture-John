package com.udemy.junit_lecture_1.testWithSpringBoot.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.udemy.junit_lecture_1.testWithSpringBoot.model.BaseItem;
import com.udemy.junit_lecture_1.testWithSpringBoot.model.BeerStyleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerDto extends BaseItem {

    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;
    private Integer quantityOnHand;
    private String price;

    @Builder
    public BeerDto(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
        String beerName, BeerStyleEnum beerStyle, Long upc, Integer quantityOnHand, String price) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.upc = upc;
        this.quantityOnHand = quantityOnHand;
        this.price = price;
    }
}
