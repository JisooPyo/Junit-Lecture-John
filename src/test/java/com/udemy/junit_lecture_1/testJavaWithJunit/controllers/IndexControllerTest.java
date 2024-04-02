package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import com.udemy.junit_lecture_1.testJavaWithJunit.ControllerTests;

class IndexControllerTest implements ControllerTests {
    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test Proper View name is returned for index page")
    @Test
    void index() {
        assertEquals("index", controller.index());

        // message
        assertEquals("index", controller.index(), "Wrong View Returned");

        // lambda
        assertEquals("index", controller.index(),
            () -> "Another Expensive Message "
                + "Make me only if you have to");

        // AssertJ 라이브러리
        assertThat(controller.index()).isEqualTo("index");
    }

    @Test
    @DisplayName("Test Exception")
    void oopsHandler() {
        assertThrows(ValueNotFoundException.class, () -> {
            controller.oopsHandler();
        });
    }

    @Disabled("Demo of Timeout")
    @Test
    void testTimeOut() {
        // 일단 다 실행시킨 후 실행 시간이 100ms를 초과하면 error 발생
        // 콘솔에 "I got here"가 출력됨
        // 실행은 단일 스레드에서 진행됨
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);

            System.out.println("I got here");
        });
    }

    @Disabled("Demo of Timeout")
    @Test
    void testTimeOutPreempt() {
        // 100ms가 되면 stop -> error 발생
        // 콘솔에 "I got here 234234234"가 출력되지 않음.
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);

            System.out.println("I got here 234234234");
        });
    }

    // Assumption: 테스트 실패시 결과가 "실패된" 상태가 되지 않고 "무시된" 상태가 된다.
    @Test
    void testAssumptionTrue() {
        // equalsIgnoreCase : 대소문자를 구분하지 않고 같은지 확인한다.
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue() {
        assumeTrue("GURU".equalsIgnoreCase("GURU"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOS() {

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows() {

    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11() {

    }

    @EnabledOnJre(JRE.JAVA_17)
    @Test
    void testMeOnJava17() {

    }

    // @EnabledIfEnvironmentVariable(named = "환경변수명", matches = "환경변수 값")
    // @Test
    // void testIf_환경변수명_is_환경변수_값() {
    //
    // }

}
