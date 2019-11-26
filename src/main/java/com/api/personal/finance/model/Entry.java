package com.api.personal.finance.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.api.personal.finance.enuns.TypesEnum;

import lombok.Data;

@Data
@Entity
@Table
@SequenceGenerator(name="entry_id", sequenceName="entry_id_seq", allocationSize=1, initialValue = 5)
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "entry_id")
	private Long id;
	@NotNull
	private String decription;
	@NotNull
	private LocalDate dateExpiry;
	private LocalDate datePayment;
	@NotNull
	private BigDecimal amount;
	private String observation;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TypesEnum types;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
}
