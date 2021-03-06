package com.api.personal.finance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "category", schema = "public")
@SequenceGenerator(name="category_id", sequenceName="category_id_seq", allocationSize=1, initialValue = 5)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "category_id")
	private Long id;
	
	@NotNull
	@Size(max = 20 , min = 3)
	private String name;
	
}
