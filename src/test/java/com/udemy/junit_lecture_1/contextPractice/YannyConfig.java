package com.udemy.junit_lecture_1.contextPractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("base-test")
public class YannyConfig {
    @Bean
    YannyWordProducer yannyWordProducer() {
        return new YannyWordProducer();
    }
}
