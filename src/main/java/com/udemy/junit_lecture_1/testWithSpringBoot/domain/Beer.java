package com.udemy.junit_lecture_1.testWithSpringBoot.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import com.udemy.junit_lecture_1.testWithSpringBoot.dto.BeerDto;
import com.udemy.junit_lecture_1.testWithSpringBoot.model.BeerStyleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beer extends BaseEntity {

    @Column(unique = true)
    private Long upc;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private Integer minOnHand;
    private Integer quantityToBrew;
    private BigDecimal price;

    @OneToMany(mappedBy = "beer")
    private Set<BeerInventory> beerInventory;

    @Builder
    public Beer(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
        Long upc, String beerName, BeerStyleEnum beerStyle, Integer minOnHand, Integer quantityToBrew, BigDecimal price,
        Set<BeerInventory> beerInventory) {
        super(id, version, createdDate, lastModifiedDate);
        this.upc = upc;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.minOnHand = minOnHand;
        this.quantityToBrew = quantityToBrew;
        this.price = price;
        this.beerInventory = beerInventory;
    }

    public BeerDto beerToBeerDto() {
        BeerDto beerDto = BeerDto.builder()
            .beerName(this.beerName)
            .beerStyle(this.beerStyle)
            .upc(this.upc)
            .quantityOnHand(this.quantityToBrew)
            .price(this.price.toString())
            .build();
        beerDto.setId(this.getId());
        beerDto.setVersion(this.getVersion());
        beerDto.setCreatedDate(this.getCreatedDate());
        beerDto.setLastModifiedDate(this.getLastModifiedDate());
        return beerDto;
    }
}
