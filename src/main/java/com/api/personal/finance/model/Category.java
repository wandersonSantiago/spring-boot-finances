package com.api.personal.finance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "category", schema = "public")
@SequenceGenerator(name="category_id", sequenceName="category_id_seq", allocationSize=1)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "category_id")
	private Long id;
	
	private String name;
	
}
