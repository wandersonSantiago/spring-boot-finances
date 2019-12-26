package com.api.personal.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.api.personal.finance.config.ApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class PersonalFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceApplication.class, args);
	}

}
