package com.udemy.junit_lecture_1.testJavaWithJunit;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// Mock 객체를 생성하는 방법 2. 어노테이션 이용 - @Mock, MockitoAnnotations.openMocks(this)로 객체 초기화
public class AnnotationMocksTest {

    @Mock
    Map<String, Object> mapMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMock() {
        mapMock.put("keyvalue", "foo");
    }
}
