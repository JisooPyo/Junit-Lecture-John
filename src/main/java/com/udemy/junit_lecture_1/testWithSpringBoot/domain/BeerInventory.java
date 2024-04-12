package com.udemy.junit_lecture_1.testWithSpringBoot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class BeerInventory extends BaseEntity {

    @ManyToOne
    private Beer beer;

    @Builder.Default
    private Integer quantityOnHand = 0;
}
