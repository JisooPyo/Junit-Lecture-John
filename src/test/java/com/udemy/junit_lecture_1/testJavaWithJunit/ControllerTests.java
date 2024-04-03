package com.udemy.junit_lecture_1.testJavaWithJunit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

// @TestInstance: 이 어노테이션을 사용하여 각 테스트 클래스에 대한 인스턴스 생성 및 관리 방법을 설정할 수 있다.
// TestInstance.Lifecycle.PER_CLASS: 각 테스트 "클래스"당 하나의 인스턴스를 생성하여 모든 테스트 메서드에서 공유한다.
//                                   => 순서가 보장되는 것은 아님.
//                                   => 처음부터 사용하는 것은 아니고 보통 중복 코드 리팩터링 시 사용하게 된다.
// 이 어노테이션을 적지 않거나, PER_METHOD(기본값)로 설정하면 메서드는 static해야 한다.
// 기본적으로 JUnit은 테스트 인스턴스를 메서드마다 새로 생성한다.(테스트의 독립성 보장을 위해)
// method가 static이 아닐 시 테스트 인스턴스 생성 시 문제가 발생할 수 있다.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("controllers")
public interface ControllerTests {

    @BeforeAll
    default void beforeAll() {
        System.out.println("Let's do something here");
    }
}
