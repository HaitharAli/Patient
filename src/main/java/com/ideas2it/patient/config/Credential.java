package com.ideas2it.patient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("db")
public class Credential {
	
	private String username;
	private String password;

}
