package com.api.personal.finance.service;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.api.personal.finance.model.Entry;
import com.api.personal.finance.repository.EntryRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EntryService {

	@Autowired
	private EntryRepository repository;
	
	
	@Transactional(readOnly = false)
	public void insert(Entry entry) {
		repository.save(entry);		
	}
	
	@Transactional(readOnly = false)
	public Entry update(Long id, Entry entry) {
		  Entry entrySave = findById(id);
		  BeanUtils.copyProperties(entry, entrySave, "id");
		  return this.repository.save(entrySave);
		}
	
	public Entry findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	public List<Entry> findAll(){
		return repository.findAll();
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		 findById(id);
		 repository.deleteById(id);
	}

	@Transactional(readOnly = false)
	public void updateStatus(Long id, Boolean status) {
		 Entry entrySave = findById(id);
		 repository.save(entrySave);
	}



	

	
	
}
