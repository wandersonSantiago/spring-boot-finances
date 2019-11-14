package com.api.personal.finance.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.api.personal.finance.model.Category;
import com.api.personal.finance.repository.CategoryRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	
	@Transactional(readOnly = false)
	public void insert(Category category) {
		repository.save(category);		
	}
	
	public Optional<Category> findById(Long id) {
		return repository.findById(id);
	}
	
	public List<Category> findAll(){
		return repository.findAll();
	}

	



	
	
}
