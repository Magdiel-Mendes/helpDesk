package com.magdiel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.magdiel.services.DbServices;

@Configuration
@Profile("test")
public class testConfig {
	@Autowired
	private DbServices dbService;
	@Bean
	public void instanciaDb() {
		this.dbService.instanciaDb();
	}
}
