package com.udemy.junit_lecture_1.testJavaWithJunit.services;

import java.util.List;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
