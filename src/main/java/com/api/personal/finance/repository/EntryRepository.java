package com.api.personal.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.personal.finance.model.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long>{

}
