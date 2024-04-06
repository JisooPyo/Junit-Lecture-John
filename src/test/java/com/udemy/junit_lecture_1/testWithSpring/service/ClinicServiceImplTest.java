package com.udemy.junit_lecture_1.testWithSpring.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.udemy.junit_lecture_1.testWithSpring.model.PetType;
import com.udemy.junit_lecture_1.testWithSpring.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {
    @Mock
    PetRepository petRepository;
    @InjectMocks
    ClinicServiceImpl service;

    @Test
    void findPetTypes() {
        // given
        List<PetType> foundPetTypes = new ArrayList<>();
        foundPetTypes.add(new PetType());
        foundPetTypes.add(new PetType());

        given(petRepository.findPetTypes()).willReturn(foundPetTypes);

        // when
        Collection<PetType> resultPetTypes = service.findPetTypes();

        // then
        then(petRepository).should().findPetTypes();
        then(petRepository).shouldHaveNoMoreInteractions();
        assertThat(resultPetTypes).hasSize(2);
    }
}
