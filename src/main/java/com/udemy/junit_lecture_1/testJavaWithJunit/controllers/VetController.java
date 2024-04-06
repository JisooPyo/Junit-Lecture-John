package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring.Model;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.VetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    public String listVets(Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
