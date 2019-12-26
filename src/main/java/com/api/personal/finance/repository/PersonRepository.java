package com.api.personal.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.personal.finance.model.Person;
import com.api.personal.finance.repository.person.PersonRepositoryQuery;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryQuery{

}
