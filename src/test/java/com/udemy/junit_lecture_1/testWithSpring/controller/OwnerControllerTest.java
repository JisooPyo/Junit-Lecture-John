package com.udemy.junit_lecture_1.testWithSpring.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.udemy.junit_lecture_1.testWithSpring.model.Owner;
import com.udemy.junit_lecture_1.testWithSpring.service.ClinicService;

@SpringBootTest
class OwnerControllerTest {

    @MockBean
    ClinicService clinicService;

    @Autowired
    OwnerController controller;

    MockMvc mockMvc;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;
    @Captor
    ArgumentCaptor<Owner> ownerArgumentCaptor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testUpdateOwnerNotValid() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", 2)
                .param("firstName", "Jimmy")
                .param("lastName", "Buffet")
                .param("telephone", "3151231234"))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("owner"))
            .andExpect(model().attributeHasFieldErrors("owner", "city"))
            .andExpect(model().attributeHasFieldErrors("owner", "address"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void testUpdateOwnerValid() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", 2)
                .param("firstName", "Jimmy")
                .param("lastName", "Buffet")
                .param("address", "123 Duval St")
                .param("city", "Key West")
                .param("telephone", "3151231234"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));

        then(clinicService).should().saveOwner(ownerArgumentCaptor.capture());
        assertThat(ownerArgumentCaptor.getValue().getId()).isEqualTo(2);
    }

    @Test
    void testNewOwnerPostValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                .param("firstName", "Jimmy")
                .param("lastName", "Buffet")
                .param("address", "123 Duval St")
                .param("city", "Key West")
                .param("telephone", "3151231234"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    void testNewOwnerPostNotValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                .param("firstName", "Jimmy")
                .param("lastName", "Buffet")
                .param("city", "Key West"))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("owner"))
            .andExpect(model().attributeHasFieldErrors("owner", "address"))
            .andExpect(model().attributeHasFieldErrors("owner", "telephone"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    @DisplayName("processFindForm - results is empty")
    void testFindByNameNotFound() throws Exception {
        mockMvc.perform(get("/owners")
                .param("lastName", "Don't find ME!"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/findOwners"));

    }

    @Test
    @DisplayName("processFindForm - lastName is null")
    void testFindByNameWhenLastNameIsNull() throws Exception {
        mockMvc.perform(get("/owners"));

        then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("");
    }

    @Test
    @DisplayName("processFindForm - results size is 1")
    void testFindByNameSizeOne() throws Exception {
        Owner owner = new Owner();
        int ownerId = 3;
        owner.setId(ownerId);
        List<Owner> owners = new ArrayList<>();
        owners.add(owner);

        String lastName = "One Result Found";

        given(clinicService.findOwnerByLastName(lastName)).willReturn(owners);

        mockMvc.perform(get("/owners").param("lastName", lastName))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/" + ownerId));

        then(clinicService).should().findOwnerByLastName(lastName);
    }

    @Test
    @DisplayName("processFindForm - multiple owners found")
    void testFindByNameMultipleOwners() throws Exception {
        List<Owner> owners = new ArrayList<>();
        owners.add(new Owner());
        owners.add(new Owner());

        given(clinicService.findOwnerByLastName("")).willReturn(owners);

        mockMvc.perform(get("/owners"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/ownersList"));

        then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("");
    }

    @Test
    void initCreationFormTest() throws Exception {
        mockMvc.perform(get("/owners/new"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("owner"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }
}
