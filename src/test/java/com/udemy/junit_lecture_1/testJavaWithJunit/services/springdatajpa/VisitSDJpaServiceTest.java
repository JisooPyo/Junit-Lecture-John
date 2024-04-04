package com.udemy.junit_lecture_1.testJavaWithJunit.services.springdatajpa;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> foundVisits = service.findAll();

        assertThat(foundVisits).hasSize(1);

        verify(visitRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Visit visit = new Visit();

        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);

        assertThat(foundVisit).isNotNull();

        verify(visitRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        Visit output = new Visit();

        when(visitRepository.save(any(Visit.class))).thenReturn(output);

        Visit savedVisit = service.save(new Visit());

        assertThat(savedVisit).isNotNull();

        verify(visitRepository, times(1)).save(any(Visit.class));
    }

    @Test
    void delete() {
        Visit visit = new Visit();

        service.delete(visit);

        verify(visitRepository, times(1)).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(visitRepository, times(1)).deleteById(anyLong());
    }
}
