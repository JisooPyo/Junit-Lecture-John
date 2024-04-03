package com.udemy.junit_lecture_1.testJavaWithJunit.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

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
    void deleteById() {
        service.deleteById(1L);
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }
}
