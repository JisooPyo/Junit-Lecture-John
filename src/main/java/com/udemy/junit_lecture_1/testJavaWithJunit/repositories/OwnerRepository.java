package com.udemy.junit_lecture_1.testJavaWithJunit.repositories;

import java.util.List;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
