package com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring;

import java.text.ParseException;
import java.util.Locale;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.PetType;

public interface Formatter<T> {

    String print(PetType petType, Locale locale);

    PetType parse(String text, Locale locale) throws ParseException;
}
