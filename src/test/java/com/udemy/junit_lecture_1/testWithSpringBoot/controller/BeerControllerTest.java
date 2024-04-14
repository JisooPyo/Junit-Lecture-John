package com.udemy.junit_lecture_1.testWithSpringBoot.controller;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.udemy.junit_lecture_1.testWithSpringBoot.dto.BeerDto;
import com.udemy.junit_lecture_1.testWithSpringBoot.model.BeerStyleEnum;
import com.udemy.junit_lecture_1.testWithSpringBoot.service.BeerService;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    BeerDto beerDto;

    @BeforeEach
    void setUp() {
        beerDto = BeerDto.builder()
            .id(UUID.randomUUID())
            .version(1L)
            .createdDate(Timestamp.from(Instant.now()))
            .lastModifiedDate(Timestamp.from(Instant.now()))
            .beerName("Beer1")
            .beerStyle(BeerStyleEnum.PALE_ALE)
            .upc(123456789012L)
            .quantityOnHand(4)
            .price(new BigDecimal("12.99").toString())
            .build();
    }

    @AfterEach
    void tearDown() {
        reset(beerService);
    }

    @Test
    void getBeerById() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        given(beerService.findBeerById(any())).willReturn(beerDto);

        mockMvc.perform(get("/api/v1/beer/" + beerDto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(beerDto.getId().toString())))
            .andExpect(jsonPath("$.beerName", is("Beer1")))
            .andExpect(jsonPath("$.createdDate",
                    is(
                        formatter.format(
                                beerDto.getCreatedDate().toInstant().atOffset(ZoneOffset.UTC)
                            )
                            .replace("Z", "+00:00")
                    )
                )
            );
        // .andExpect(jsonPath("$.id").value(beerDto.getId().toString()));
    }

    @DisplayName("List Ops - ")
    @Nested
    public class TestListOperations {
        @Captor
        ArgumentCaptor<String> beerNameCaptor;
        @Captor
        ArgumentCaptor<BeerStyleEnum> beerStyleEnumCaptor;

        List<BeerDto> beerList;

        @BeforeEach
        void setUp() {
            beerList = new ArrayList<>();
            beerList.add(beerDto);
            beerList.add(BeerDto.builder()
                .id(UUID.randomUUID())
                .version(1L)
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .beerName("Beer4")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .upc(123123123122L)
                .quantityOnHand(66)
                .price(new BigDecimal("12.99").toString())
                .build());

            given(beerService.listBeers(beerNameCaptor.capture(), beerStyleEnumCaptor.capture()))
                .willReturn(beerList);
        }

        @DisplayName("Test list beers - no parameters")
        @Test
        void testListBeers() throws Exception {
            mockMvc.perform(get("/api/v1/beer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(beerDto.getId().toString())));
        }
    }
}
