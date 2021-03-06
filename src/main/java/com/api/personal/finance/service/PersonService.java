package com.api.personal.finance.service;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.api.personal.finance.model.Person;
import com.api.personal.finance.repository.PersonRepository;
import com.api.personal.finance.repository.filter.PersonFilter;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	
	@Transactional(readOnly = false)
	public void insert(Person person) {
		repository.save(person);		
	}
	
	@Transactional(readOnly = false)
	public Person update(Long id, Person person) {
		  Person personSave = findById(id);
		  BeanUtils.copyProperties(person, personSave, "id");
		  return this.repository.save(personSave);
		}
	
	public Person findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	public List<Person> findAll(){
		return repository.findAll();
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		 findById(id);
		 repository.deleteById(id);
	}

	@Transactional(readOnly = false)
	public void updateStatus(Long id, Boolean status) {
		 Person personSave = findById(id);
		 personSave.setStatus(status);
		 repository.save(personSave);
	}

	public Page<Person> searchPersonFilter(PersonFilter filter, Pageable page) {
		return repository.filter(filter, page);
	}

	
}