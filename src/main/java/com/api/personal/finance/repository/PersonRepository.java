package com.api.personal.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.personal.finance.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
