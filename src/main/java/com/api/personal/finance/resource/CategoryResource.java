package com.api.personal.finance.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.personal.finance.event.CreatedResourceEvent;
import com.api.personal.finance.model.Category;
import com.api.personal.finance.service.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	@Autowired
	private ApplicationEventPublisher publisher;
	
		
	@PostMapping
	public ResponseEntity<Category> insert(@Valid @RequestBody Category category, HttpServletResponse response) {
		service.insert(category);
		publisher.publishEvent(new CreatedResourceEvent(this, response, category.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@PathVariable Long id, @Valid @RequestBody Category category, HttpServletResponse response) {
		Category  c = service.update(id, category);
		publisher.publishEvent(new CreatedResourceEvent(this, response, c.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}

	@GetMapping("/{id}")
	public Category findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping
	public List<Category> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
		
	}
}
