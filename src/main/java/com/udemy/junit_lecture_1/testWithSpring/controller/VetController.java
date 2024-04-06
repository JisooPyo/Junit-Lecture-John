package com.udemy.junit_lecture_1.testWithSpring.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udemy.junit_lecture_1.testWithSpring.model.Vets;
import com.udemy.junit_lecture_1.testWithSpring.service.ClinicService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class VetController {
    private final ClinicService clinicService;

    @RequestMapping(value = {"/vets.html"})
    public String showVetList(Map<String, Object> model) {
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        model.put("vets", vets);
        return "vets/vetList";
    }

    @RequestMapping(value = {"/vets.json", "/vets.xml"})
    @ResponseBody
    public Vets showResourcesVetList() {
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        return vets;
    }

}
