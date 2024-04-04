package com.udemy.junit_lecture_1.testJavaWithJunit.services.springdatajpa;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Speciality;
import com.udemy.junit_lecture_1.testJavaWithJunit.repositories.SpecialtyRepository;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {
    // 의존하는 객체를 대체하기 위해 사용된다.
    @Mock
    SpecialtyRepository specialtyRepository;

    // 테스트 대상 객체에 Mock을 주입하기 위해 사용된다.
    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();

        service.delete(speciality);

        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findByIdTest() {
        Speciality speciality = new Speciality();

        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpecialty = service.findById(1L);

        assertThat(foundSpecialty).isNotNull();

        // any...() -> anyLong, anyBoolean, ...
        // verify(specialtyRepository, times(1)).findById(anyLong());
        verify(specialtyRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdBddTest() {
        Speciality speciality = new Speciality();

        // given
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when
        Speciality foundSpecialty = service.findById(1L);

        // then
        assertThat(foundSpecialty).isNotNull();
        then(specialtyRepository).should().findById(anyLong());     // default: should(times(1))
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAAtLeast() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }
}
