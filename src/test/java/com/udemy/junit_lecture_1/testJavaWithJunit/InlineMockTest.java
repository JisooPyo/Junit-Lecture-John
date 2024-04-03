package com.udemy.junit_lecture_1.testJavaWithJunit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

// Mock 객체를 생성하는 방법 1. mock 메서드 이용 - mock(Class<T>)
public class InlineMockTest {
    @Test
    void testInlineMock() {
        Map mapMock = mock(Map.class);

        assertEquals(mapMock.size(), 0);
    }
}
