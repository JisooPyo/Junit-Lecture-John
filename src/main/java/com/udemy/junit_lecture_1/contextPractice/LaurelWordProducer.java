package com.udemy.junit_lecture_1.contextPractice;

import org.springframework.stereotype.Component;

@Component
public class LaurelWordProducer implements WordProducer {
    @Override
    public String getWord() {
        return "Laurel";
    }
}
