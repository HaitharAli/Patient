package com.ideas2it.patient.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class UserStatusCountContributor implements InfoContributor{

	@Override
	public void contribute(Builder builder) {
		Map<String,Long> userMap = new HashMap<>();
		userMap.put("active", (long) 10);
		userMap.put("inActive", (long) 10);
		builder.withDetail("userStatus", userMap);
		
	}

}
