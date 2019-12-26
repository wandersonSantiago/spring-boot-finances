package com.api.personal.finance.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.personal.finance.enuns.TypesEnum;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EntryResume {
	
	private Long id;
	private String description;
	private LocalDate dateExpiry;
	private LocalDate datePayment;
	private BigDecimal amount;
	private TypesEnum type;
	private String category;
	private String person;
	
	
	public EntryResume(Long id, String description, LocalDate dateExpiry, LocalDate datePayment, BigDecimal amount,
			TypesEnum type, String category, String person) {
		super();
		this.id = id;
		this.description = description;
		this.dateExpiry = dateExpiry;
		this.datePayment = datePayment;
		this.amount = amount;
		this.type = type;
		this.category = category;
		this.person = person;
	}
	
	

}
