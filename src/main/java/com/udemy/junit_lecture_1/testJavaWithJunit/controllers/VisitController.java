package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import java.util.Map;

import com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring.BindingResult;
import com.udemy.junit_lecture_1.testJavaWithJunit.fauxspring.WebDataBinder;
import com.udemy.junit_lecture_1.testJavaWithJunit.model.Pet;
import com.udemy.junit_lecture_1.testJavaWithJunit.model.Visit;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.PetService;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.VisitService;

import jakarta.validation.Valid;

public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    public Visit loadPetWithVisit(Long petId, Map<String, Object> model) {
        Pet pet = petService.findById(petId);
        model.put("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    public String initNewVisitForm(Long petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }

    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);

            return "redirect:/owners/{ownerId}";
        }
    }
}
