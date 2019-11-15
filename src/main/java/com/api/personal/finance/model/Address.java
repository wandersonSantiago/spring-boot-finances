package com.api.personal.finance.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	
	private String street;
	private String number;
	private String complement;
	private String district;
	private String zipCode;
	private String city;
	private String state;
	private String country;

}
