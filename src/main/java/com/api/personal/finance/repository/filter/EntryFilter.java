package com.api.personal.finance.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryFilter {

	
	private String decription;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateExpiryOf;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateExpiryUntil;
	
}
