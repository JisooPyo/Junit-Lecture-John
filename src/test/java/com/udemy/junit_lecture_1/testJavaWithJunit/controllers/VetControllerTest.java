package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring.Model;
import com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring.ModelMapImpl;
import com.udemy.junit_lecture_1.testJavaWithJunit.model.Vet;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.SpecialtyService;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.VetService;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.map.SpecialityMapService;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.map.VetMapService;

class VetControllerTest {
    VetService vetService;
    SpecialtyService specialtyService;
    VetController vetController;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);

        vetController = new VetController(vetService);

        Vet vet1 = new Vet(1L, "joe", "buck", null);
        Vet vet2 = new Vet(2L, "Jimmy", "Smith", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {
        Model model = new ModelMapImpl();

        String view = vetController.listVets(model);

        assertThat("vets/index").isEqualTo(view);
        Set modelAttribute = (Set)((ModelMapImpl)model).getMap().get("vets");

        assertThat(modelAttribute.size()).isEqualTo(2);
    }
}
