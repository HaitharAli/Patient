package com.ideas2it.patient.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ideas2it.patient.dto.UserDto;

@FeignClient("user-service")
public interface UserFeignService {

	@GetMapping("/getByUserId/{userId}")
	ResponseEntity<UserDto> getUserDetailsByUserId(@PathVariable String userId);

}
