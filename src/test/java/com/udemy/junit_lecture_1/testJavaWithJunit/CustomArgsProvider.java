package com.udemy.junit_lecture_1.testJavaWithJunit;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CustomArgsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
            Arguments.of("FL", 7, 8),
            Arguments.of("OH", 9, 10),
            Arguments.of("MI", 11, 12));
    }
}
