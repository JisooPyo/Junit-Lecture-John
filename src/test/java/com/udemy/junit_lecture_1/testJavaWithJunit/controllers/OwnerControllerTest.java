package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring.BindingResult;
import com.udemy.junit_lecture_1.testJavaWithJunit.model.Owner;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    @Mock
    OwnerService ownerService;
    @Mock
    BindingResult bindingResult;
    @InjectMocks
    OwnerController controller;

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner(5L, "Ember", "Brown");
    }

    @Test
    void processCreationFormHasErrors() {
        // given
        given(bindingResult.hasErrors()).willReturn(true);

        // when
        String viewName = controller.processCreationForm(owner, bindingResult);

        // then
        assertEquals(VIEWS_OWNER_CREATE_OR_UPDATE_FORM, viewName);
        assertThat(viewName).isEqualTo(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
    }

    @Test
    void processCreationFormNoErrors() {
        // given
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(owner)).willReturn(owner);

        // when
        String viewName = controller.processCreationForm(owner, bindingResult);

        // then
        then(ownerService).should().save(owner);
        then(ownerService).shouldHaveNoMoreInteractions();

        assertEquals(REDIRECT_OWNERS_5, viewName);
        assertThat(viewName).isEqualTo(REDIRECT_OWNERS_5);
    }
}
