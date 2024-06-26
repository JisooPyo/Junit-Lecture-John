package com.udemy.junit_lecture_1.testWithSpring.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.udemy.junit_lecture_1.testWithSpring.model.Vet;
import com.udemy.junit_lecture_1.testWithSpring.model.Vets;
import com.udemy.junit_lecture_1.testWithSpring.service.ClinicService;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    ClinicService clinicService;
    @Mock
    Map<String, Object> model;
    @InjectMocks
    VetController controller;

    List<Vet> foundVets = new ArrayList<>();
    MockMvc mockMvc;        // HTTP 요청을 시뮬레이션할 수 있다.

    @BeforeEach
    void setUp() {
        foundVets.add(new Vet());
        foundVets.add(new Vet());
        foundVets.add(new Vet());

        // given
        given(clinicService.findVets()).willReturn(foundVets);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testControllerShowVetList() throws Exception {
        mockMvc.perform(get("/vets.html"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("vets"))
            .andExpect(view().name("vets/vetList"));
    }

    @Test
    void showVetList() {
        // when
        String viewName = controller.showVetList(model);

        // then
        String SHOW_VET_LIST_VIEW = "vets/vetList";

        then(clinicService).should().findVets();
        then(clinicService).shouldHaveNoMoreInteractions();
        then(model).should().put(anyString(), any());
        assertEquals(SHOW_VET_LIST_VIEW, viewName);
    }

    @Test
    void showResourcesVetList() {
        // when
        Vets vets = controller.showResourcesVetList();

        // then
        then(clinicService).should().findVets();
        then(clinicService).shouldHaveNoMoreInteractions();
        assertThat(vets.getVetList()).hasSize(3);
    }
}
