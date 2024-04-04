package com.udemy.junit_lecture_1.testJavaWithJunit;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Mock 객체를 생성하는 방법 3. 어노테이션 이용 - @Mock, Extension으로 객체 초기화
@ExtendWith(MockitoExtension.class)
public class JUnitExtensionTest {
    @Mock
    Map<String, Object> mapMock;

    @Test
    void testMock() {
        mapMock.put("keyvalue", "foo");
    }

}
