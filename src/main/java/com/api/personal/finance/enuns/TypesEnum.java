package com.api.personal.finance.enuns;

public enum TypesEnum {
	
	RECEITA(1,"Receita"),
	DESPESA(2, "Despesa");
	

	private Integer id;
	private String value;


	 TypesEnum(Integer id, String value){
		this.id = id;
		this.value = value;
	}


	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	 
	 
}
