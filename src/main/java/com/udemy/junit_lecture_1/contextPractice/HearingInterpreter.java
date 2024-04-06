package com.udemy.junit_lecture_1.contextPractice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HearingInterpreter {

    private final WordProducer wordProducer;

    public String whatIHeard() {
        String word = wordProducer.getWord();

        System.out.println(word);

        return word;
    }
}
