package com.ideas2it.patient.config;

import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {
	
	@Autowired
	private Credential credential;

	@Bean
	public DataSource getDataSource() throws URISyntaxException {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		
		String username = credential.getUsername();
		String password = credential.getPassword();
		System.out.println("username "+username +" password "+password);
		dataSourceBuilder.url("jdbc:mysql://host.docker.internal:3306/hospital");
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		return dataSourceBuilder.build();
	}

}