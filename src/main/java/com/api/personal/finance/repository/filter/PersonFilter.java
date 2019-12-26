package com.api.personal.finance.repository.filter;

import com.api.personal.finance.model.Address;

import lombok.Data;

@Data
public class PersonFilter {
	
	private Long id;
	private String name;
	private Address address;
	private Boolean status;

}
