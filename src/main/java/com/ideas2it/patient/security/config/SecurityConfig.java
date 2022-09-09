package com.ideas2it.patient.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ideas2it.patient.filter.JwtRequestFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//No need to extend WebSecurityConfigurerAdapter since that is deprecated
public class SecurityConfig {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	// For authorization we need to create bean for SecurityFilterChain class
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// No need to add Userdetails in security config in new Security feature

	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsServices() { UserDetails
	 * user =
	 * User.withDefaultPasswordEncoder().username("user").password("password").roles
	 * ("USER") .build(); return new
	 * InMemoryUserDetailsManager(myUserDetailsService.loadUserByUsername("user"));
	 * }
	 */

	/*
	 * @Bean public UserDetailsService userDetailsService() { return
	 * myUserDetailsService; }
	 */

	// need to add xml.bind jar to prevent JAXB Exception
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
