package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring.BindingResult;
import com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring.Model;
import com.udemy.junit_lecture_1.testJavaWithJunit.model.Owner;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.OwnerService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OwnerControllerTest {
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    @Mock
    OwnerService ownerService;
    @Mock
    BindingResult bindingResult;
    @InjectMocks
    OwnerController controller;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willAnswer(
            invocation -> {
                List<Owner> owners = new ArrayList<>();

                String name = invocation.getArgument(0);

                if (name.equals("%Brown%")) {
                    owners.add(new Owner(1L, "Joe", "Brown"));
                    return owners;
                } else if (name.equals("%DontFindMe%")) {
                    return owners;
                } else if (name.equals("%FindMe%")) {
                    owners.add(new Owner(1L, "Joe", "Brown"));
                    owners.add(new Owner(2L, "Joe2", "Brown2"));
                    return owners;
                }

                throw new RuntimeException("Invalid Argument");
            }
        );
    }

    @Test
    void processFindFormWildCardFound() {
        // given
        Owner owner = new Owner(1L, "Joe", "FindMe");

        // when
        String viewName = controller.processFindForm(owner, bindingResult, Mockito.mock(Model.class));

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%FindMe%");
        assertThat("owners/ownersList").isEqualTo(viewName);
    }

    @Test
    void processFindFormWildCardStringAnnotation() {
        // given
        Owner owner = new Owner(1L, "Joe", "Brown");

        // when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%Brown%");
        assertThat("redirect:/owners/1").isEqualTo(viewName);
    }

    @Test
    void processFindFormWildCardNotFound() {
        // given
        Owner owner = new Owner(1L, "Joe", "DontFindMe");

        // when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%DontFindMe%");
        assertThat("owners/findOwners").isEqualTo(viewName);
    }

    @Test
    void processCreationFormHasErrors() {
        // given
        Owner owner = new Owner(5L, "Ember", "Brown");
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
        Owner owner = new Owner(5L, "Ember", "Brown");
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
