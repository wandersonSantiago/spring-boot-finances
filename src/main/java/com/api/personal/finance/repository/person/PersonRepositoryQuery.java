package com.api.personal.finance.repository.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.personal.finance.model.Person;
import com.api.personal.finance.repository.filter.PersonFilter;

public interface PersonRepositoryQuery {

	
	public Page<Person> filter(PersonFilter filter, Pageable page);
}
