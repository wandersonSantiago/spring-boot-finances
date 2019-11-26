package com.api.personal.finance.service;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	@Transactional(readOnly = false)
	public Category update(Long id, Category category) {
		Category categorySave = findById(id);
		  BeanUtils.copyProperties(category, categorySave, "id");
		  return this.repository.save(categorySave);
		}
	
	
	public Category findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	public List<Category> findAll(){
		return repository.findAll();
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

}