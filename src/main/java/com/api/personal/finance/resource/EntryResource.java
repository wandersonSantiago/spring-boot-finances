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
import com.api.personal.finance.model.Entry;
import com.api.personal.finance.repository.filter.EntryFilter;
import com.api.personal.finance.repository.projection.EntryResume;
import com.api.personal.finance.service.EntryService;

@RestController
@RequestMapping(value = "/entries")
public class EntryResource {

	@Autowired
	private EntryService service;
	@Autowired
	private ApplicationEventPublisher publisher;
	
		
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CREATE_ENTRY') and #oauth2.hasScope('write')")
	public ResponseEntity<Entry> insert(@Valid @RequestBody Entry entry, HttpServletResponse response) {
		Entry entrySave = service.insert(entry);
		publisher.publishEvent(new CreatedResourceEvent(this, response, entry.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(entrySave);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CREATE_ENTRY') and #oauth2.hasScope('write')")
	public ResponseEntity<Entry> update(@PathVariable Long id, @Valid @RequestBody Entry entry, HttpServletResponse response) {
		Entry p = service.update(id, entry);
		publisher.publishEvent(new CreatedResourceEvent(this, response, p.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	
	@PutMapping("/{id}/status")
	@PreAuthorize("hasAuthority('ROLE_CREATE_ENTRY') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long id, @RequestBody Boolean status) {
		service.updateStatus(id, status);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_ENTRY') and #oauth2.hasScope('read')")
	public Entry findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_ENTRY') and #oauth2.hasScope('read')")
	public Page<Entry> searchEntryFilter(EntryFilter filter, Pageable page) {
		return service.searchEntryFilter(filter, page);
	}

	@GetMapping("/resume")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_ENTRY') and #oauth2.hasScope('read')")
	public Page<EntryResume> searchEntryFilterResume(EntryFilter filter, Pageable page) {
		return service.searchEntryFilterResume(filter, page);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVE_ENTRY') and #oauth2.hasScope('write')")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);		
	}
}
