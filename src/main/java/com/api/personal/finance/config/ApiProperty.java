package com.api.personal.finance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("finances")
@Getter@Setter
public class ApiProperty {

	private String originAllow;
	
	private final Security security = new Security();
	
	
	
	@Getter@Setter
	public static  class Security{		
		private boolean enableHttps;
		
	}
}
