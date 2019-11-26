package com.api.personal.finance.repository.entry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.api.personal.finance.model.Entry;
import com.api.personal.finance.repository.filter.EntryFilter;

public class EntryRepositoryImpl implements EntryRepositoryQuery{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Entry> filter(EntryFilter filter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);
		
		Root<Entry> root = criteria.from(Entry.class);
		root.fetch("person");
		root.fetch("category");
		
		Predicate[] predicates = createConstraints(filter, builder, root);
		
		criteria.where(predicates);
		TypedQuery<Entry> query = entityManager.createQuery(criteria);
		
		addPagingRestrictions(query, pageable);
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Predicate[] createConstraints(EntryFilter filter, CriteriaBuilder builder, Root<Entry> root) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filter.getDecription())) {
			predicates.add(builder.like( builder.lower(root.get("decription")), "%" + filter.getDecription().toLowerCase() + "%"));
		}
		if(filter.getDateExpiryOf() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dateExpiry"), filter.getDateExpiryOf()));
		}
		if(filter.getDateExpiryUntil() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dateExpiry"), filter.getDateExpiryUntil()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void addPagingRestrictions(TypedQuery<Entry> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstPageRegistration = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstPageRegistration);
		query.setMaxResults(totalRecordsPerPage);
	}
	
	private Long total(EntryFilter lancamentoFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Entry> root = criteria.from(Entry.class);
		
		Predicate[] predicates = createConstraints(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return entityManager.createQuery(criteria).getSingleResult();
	}

}
