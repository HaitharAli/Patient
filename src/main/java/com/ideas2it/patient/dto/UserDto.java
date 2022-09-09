package com.ideas2it.patient.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable {

	private static final long serialVersionUID = -1530311220388659890L;
	private Integer userId;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String userSID;
	private String password;
	// private List<Role> userRoles;

}
