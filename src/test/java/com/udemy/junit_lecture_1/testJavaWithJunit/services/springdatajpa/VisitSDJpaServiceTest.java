package com.udemy.junit_lecture_1.testJavaWithJunit.services.springdatajpa;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Visit;
import com.udemy.junit_lecture_1.testJavaWithJunit.repositories.VisitRepository;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJpaService service;

    @Test
    @DisplayName("Test Find All")
    void findAll() {
        // given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        given(visitRepository.findAll()).willReturn(visits);

        // when
        Set<Visit> foundVisits = service.findAll();

        // then
        assertThat(foundVisits).hasSize(1);
        then(visitRepository).should().findAll();
    }

    @Test
    void findById() {
        // given
        Visit visit = new Visit();

        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

        // when
        Visit foundVisit = service.findById(1L);

        // then
        assertThat(foundVisit).isNotNull();
        then(visitRepository).should().findById(anyLong());
    }

    @Test
    void save() {
        // given
        Visit output = new Visit();

        given(visitRepository.save(any(Visit.class))).willReturn(output);

        // when
        Visit savedVisit = service.save(new Visit());

        // then
        assertThat(savedVisit).isNotNull();

        then(visitRepository).should().save(any(Visit.class));
    }

    @Test
    void delete() {
        // given
        Visit visit = new Visit();

        // when
        service.delete(visit);

        // then
        then(visitRepository).should().delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        // when
        service.deleteById(1L);

        // then
        then(visitRepository).should().deleteById(anyLong());
    }
}
