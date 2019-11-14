package com.api.personal.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.personal.finance.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
