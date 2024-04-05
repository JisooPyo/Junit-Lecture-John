package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    void processFindFormWildCardStringAnnotation() {
        // given
        Owner owner = new Owner(5L, "Joe", "Brown");
        List<Owner> ownerList = new ArrayList<>();
        // 메서드 호출에 사용되는 인자에 대해 검증하고 싶을 때(이 경우, String 인자)
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        // when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%Brown%");
    }

    @Test
    void processFindFormWildCardString() {
        // given
        Owner owner = new Owner(5L, "Joe", "Brown");
        List<Owner> ownerList = new ArrayList<>();
        // 메서드 호출에 사용되는 인자에 대해 검증하고 싶을 때(이 경우, String 인자)
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

        // when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        // then
        assertThat(captor.getValue()).isEqualTo("%Brown%");
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
