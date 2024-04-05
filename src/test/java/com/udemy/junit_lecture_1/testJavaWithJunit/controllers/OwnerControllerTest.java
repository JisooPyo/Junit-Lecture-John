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
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    Model model;
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
        // 메서드 호출의 순서를 검증
        InOrder inOrder = inOrder(ownerService, model);

        // when
        String viewName = controller.processFindForm(owner, bindingResult, model);

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%FindMe%");
        assertThat("owners/ownersList").isEqualTo(viewName);

        // inorder asserts - 순서는 inOrder() 메서드 인자 순이 아니라 verify 순으로 결정
        // 아래 두 코드가 위치가 반대로 되어 있으면 에러가 난다.
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model, times(1)).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);
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
        verifyNoInteractions(model);
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
        verifyNoInteractions(model);
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
