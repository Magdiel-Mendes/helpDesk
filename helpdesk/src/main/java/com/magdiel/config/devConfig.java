package com.magdiel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.magdiel.services.DbServices;

@Configuration
@Profile("dev")
public class devConfig {
	@Autowired
	private DbServices dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	@Bean
	public Boolean instanciaDb() {
		if(value.equals("create")) {
		this.dbService.instanciaDb();
		}
		return false;
	}
}
