package com.udemy.junit_lecture_1.contextPractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("base-test")
@Configuration
public class BaseConfig {
    @Bean
    HearingInterpreter hearingInterpreter(WordProducer wordProducer) {
        return new HearingInterpreter(wordProducer);
    }
}
