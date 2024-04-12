package com.udemy.junit_lecture_1.testWithSpringBoot.controller;

import static org.hamcrest.core.Is.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.udemy.junit_lecture_1.testWithSpringBoot.dto.BeerDto;
import com.udemy.junit_lecture_1.testWithSpringBoot.model.BeerStyleEnum;
import com.udemy.junit_lecture_1.testWithSpringBoot.service.BeerService;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class BeerControllerTest {
    @Mock
    BeerService beerService;
    @InjectMocks
    BeerController controller;

    MockMvc mockMvc;
    BeerDto beerDto;

    @BeforeEach
    void setUp() {
        beerDto = BeerDto.builder()
            .id(UUID.randomUUID())
            .version(1L)
            .createdDate(Timestamp.valueOf(LocalDateTime.now()))
            .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
            .beerName("Beer1")
            .beerStyle(BeerStyleEnum.PALE_ALE)
            .upc(123456789012L)
            .quantityOnHand(4)
            .price(new BigDecimal("12.99").toString())
            .build();

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getBeerById() throws Exception {
        given(beerService.findBeerById(any())).willReturn(beerDto);

        mockMvc.perform(get("/api/v1/beer/" + beerDto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(beerDto.getId().toString())))
            .andExpect(jsonPath("$.beerName", is("Beer1")));
        // .andExpect(jsonPath("$.id").value(beerDto.getId().toString()));
    }
}
