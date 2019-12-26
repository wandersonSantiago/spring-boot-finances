package com.api.personal.finance.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.api.personal.finance.repository.filter.PersonFilter;
import com.api.personal.finance.service.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonResource {

	@Autowired
	private PersonService service;
	@Autowired
	private ApplicationEventPublisher publisher;
	
		
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CREATE_PERSON') and #oauth2.hasScope('write')")
	public ResponseEntity<Person> insert(@Valid @RequestBody Person person, HttpServletResponse response) {
		service.insert(person);
		publisher.publishEvent(new CreatedResourceEvent(this, response, person.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CREATE_PERSON') and #oauth2.hasScope('write')")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person, HttpServletResponse response) {
		Person p = service.update(id, person);
		publisher.publishEvent(new CreatedResourceEvent(this, response, p.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	
	@PutMapping("/{id}/status")
	@PreAuthorize("hasAuthority('ROLE_CREATE_PERSON') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long id, @RequestBody Boolean status) {
		service.updateStatus(id, status);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")
	public Person findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")
	public Page<Person> searchPersonFilter(PersonFilter filter, Pageable page) {
		return service.searchPersonFilter(filter, page);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVE_PERSON') and #oauth2.hasScope('write')")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);		
	}
}
