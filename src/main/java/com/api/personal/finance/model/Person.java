package com.api.personal.finance.model;

import javax.persistence.Embedded;
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
@Table(name = "person", schema = "public")

public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "person_id")
	@SequenceGenerator(name="person_id", sequenceName="person_id_seq", allocationSize=1, initialValue = 5)
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 40)
	private String name;
	
	@Embedded
	private Address address;
	
	@NotNull
	private Boolean status;
}
