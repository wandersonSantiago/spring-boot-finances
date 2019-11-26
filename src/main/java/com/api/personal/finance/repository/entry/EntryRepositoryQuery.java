package com.api.personal.finance.repository.entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.personal.finance.model.Entry;
import com.api.personal.finance.repository.filter.EntryFilter;

public interface EntryRepositoryQuery {

	
	public Page<Entry> filter(EntryFilter filter, Pageable page);
}
