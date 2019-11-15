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
import com.api.personal.finance.model.Person;
import com.api.personal.finance.service.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonResource {

	@Autowired
	private PersonService service;
	@Autowired
	private ApplicationEventPublisher publisher;
	
		
	@PostMapping
	public ResponseEntity<Person> insert(@Valid @RequestBody Person person, HttpServletResponse response) {
		service.insert(person);
		publisher.publishEvent(new CreatedResourceEvent(this, response, person.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person, HttpServletResponse response) {
		Person p = service.update(id, person);
		publisher.publishEvent(new CreatedResourceEvent(this, response, p.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	
	@PutMapping("/{id}/status")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long id, @RequestBody Boolean status) {
		service.updateStatus(id, status);
	}

	@GetMapping("/{id}")
	public Person findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping
	public List<Person> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);		
	}
}