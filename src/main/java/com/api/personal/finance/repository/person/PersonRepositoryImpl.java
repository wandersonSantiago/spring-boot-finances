package com.api.personal.finance.repository.person;

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

import com.api.personal.finance.model.Person;
import com.api.personal.finance.repository.filter.PersonFilter;

public class PersonRepositoryImpl implements PersonRepositoryQuery{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Person> filter(PersonFilter filter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
		
		Root<Person> root = criteria.from(Person.class);
		
		Predicate[] predicates = createConstraints(filter, builder, root);
		
		criteria.where(predicates);
		TypedQuery<Person> query = entityManager.createQuery(criteria);
		
		addPagingRestrictions(query, pageable);
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}
	
	
	private Predicate[] createConstraints(PersonFilter filter, CriteriaBuilder builder, Root<Person> root) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filter.getName())) {
			predicates.add(builder.like( builder.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
		}
		if(filter.getStatus() != null) {
			predicates.add(builder.equal(root.get("status"), filter.getStatus()));
		}
		
		if(filter.getAddress() != null) {
			if(filter.getAddress().getCity() != null) {				
				predicates.add(builder.equal(root.get("address").get("city"), filter.getAddress().getCity()));
			}
			if(filter.getAddress().getState() != null) {				
				predicates.add(builder.equal(root.get("address").get("state"), filter.getAddress().getState()));
			}
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void addPagingRestrictions(TypedQuery<?> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstPageRegistration = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstPageRegistration);
		query.setMaxResults(totalRecordsPerPage);
	}
	
	private Long total(PersonFilter lancamentoFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate[] predicates = createConstraints(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return entityManager.createQuery(criteria).getSingleResult();
	}

}
