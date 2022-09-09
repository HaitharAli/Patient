package com.ideas2it.patient.security.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideas2it.patient.dto.UserDto;
import com.ideas2it.patient.external.UserFeignService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserFeignService feignService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("inside userdetails");
		UserDto userDto = feignService.getUserDetailsByUserId(username).getBody();

		System.out.println("user " + userDto.getPassword());
		return new User(userDto.getUserSID(), new BCryptPasswordEncoder().encode(userDto.getPassword()),
				new ArrayList<>());
	}

}
