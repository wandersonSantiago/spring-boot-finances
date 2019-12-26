package com.api.personal.finance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
@Table(name="permission")
public class Permission {

	@Id
	@SequenceGenerator(name="permission_id", sequenceName="permission_id_seq", allocationSize=1, initialValue = 9)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "permission_id")
	private Long id;
	
	private String description;
	
	
}
