package com.api.personal.finance.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@SequenceGenerator(name="user_id", sequenceName="user_id_seq", allocationSize=1, initialValue = 3)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id")
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_permission", joinColumns = @JoinColumn(name="id_user"),
	inverseJoinColumns = @JoinColumn(name="id_permission"))
	private List<Permission> permissions;
	
}
