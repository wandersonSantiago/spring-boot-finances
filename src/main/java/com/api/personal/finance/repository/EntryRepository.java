package com.api.personal.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.personal.finance.model.Entry;
import com.api.personal.finance.repository.entry.EntryRepositoryQuery;

public interface EntryRepository extends JpaRepository<Entry, Long>, EntryRepositoryQuery{

}
