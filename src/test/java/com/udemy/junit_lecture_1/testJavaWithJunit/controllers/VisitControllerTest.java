package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Pet;
import com.udemy.junit_lecture_1.testJavaWithJunit.model.Visit;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.VisitService;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.map.PetMapService;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    @Mock
    VisitService visitService;
    @Spy        // 객체의 일부 메서드를 실제로 호출하고 싶을 때. 자주 쓰이지는 않음.
    PetMapService petService;
    @InjectMocks
    VisitController visitController;

    @Test
    void loadPetWithVisit() {
        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(12L);
        Pet pet3 = new Pet(3L);
        petService.save(pet);
        petService.save(pet3);

        given(petService.findById(anyLong())).willCallRealMethod();

        // when
        Visit visit = visitController.loadPetWithVisit(12L, model);

        // then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(12L);
        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void loadPetWithVisitWithStubbing() {
        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(12L);
        Pet pet3 = new Pet(3L);
        petService.save(pet);
        petService.save(pet3);

        given(petService.findById(anyLong())).willReturn(pet3);

        // when
        // 12L로 저장된 pet이 있지만 given에서 pet3을 return하라고 정의했기 때문에 그를 먼저 따른다.
        Visit visit = visitController.loadPetWithVisit(12L, model);

        // then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(3L);
        verify(petService, times(1)).findById(anyLong());
    }

}
