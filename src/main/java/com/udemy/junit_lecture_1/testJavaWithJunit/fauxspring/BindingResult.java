package com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring;

public interface BindingResult {
    void rejectValue(String lastName, String notFound, String not_found);

    boolean hasErrors();
}
