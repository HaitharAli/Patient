package com.ideas2it.patient.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.patient.dto.AuthenticationRequest;
import com.ideas2it.patient.dto.AuthenticationResponse;
import com.ideas2it.patient.dto.PaginationRequest;
import com.ideas2it.patient.dto.PatientDto;
import com.ideas2it.patient.dto.PatientInputDto;
import com.ideas2it.patient.exception.InvalidUserException;
import com.ideas2it.patient.security.config.MyUserDetailsService;
import com.ideas2it.patient.service.PatientService;
import com.ideas2it.patient.util.JwtUtil;

@RestController
public class PatientController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	Logger logger = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@GetMapping("hello")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);

	}

	@GetMapping("/getPatientById/{id}")
	public ResponseEntity<Optional<PatientDto>> getPatientById(@PathVariable Long id,
			@RequestParam String loginUserId) {
		return new ResponseEntity<Optional<PatientDto>>(patientService.getPatientById(id, loginUserId), HttpStatus.OK);

	}

	@GetMapping("/getAllPatients")
	public ResponseEntity<List<PatientDto>> getAllPatients(@RequestParam String loginUserId) {
		return new ResponseEntity<List<PatientDto>>(patientService.getAllPatients(loginUserId), HttpStatus.OK);
	}

	@GetMapping("/getAllPatientsPaginated")
	ResponseEntity<List<PatientDto>> getAllPatientsPaginated(@RequestBody PaginationRequest paginationRequest,
			@RequestParam String loginUserId) {
		logger.info("UserRoleController::getAllPatientsPaginated - Start");
		return new ResponseEntity<List<PatientDto>>(patientService.getAllPatientsPaginated(
				paginationRequest.getPageNo(), paginationRequest.getPageSize(), loginUserId), HttpStatus.OK);

	}

	@PostMapping("/addPatientInfo")
	public ResponseEntity<PatientDto> addPatientInfo(@RequestBody PatientInputDto patientInputDto,
			@RequestParam String loginUserId) {
		System.out.println("patient dto controller " + patientInputDto);

		return new ResponseEntity<PatientDto>(patientService.addPatient(patientInputDto, loginUserId),
				HttpStatus.CREATED);

	}

	@PutMapping("/updatePatientInfo/{id}")
	public ResponseEntity<PatientDto> updatePatientInfo(@PathVariable Long id,
			@RequestBody PatientInputDto patientInputDto, @RequestParam String loginUserId) {

		return new ResponseEntity<PatientDto>(patientService.updatePatient(id, patientInputDto, loginUserId),
				HttpStatus.ACCEPTED);
	}

	@PatchMapping("/patchPatientInfo/{id}")
	public ResponseEntity<PatientDto> patchPatientInfo(@PathVariable Long id,
			@RequestBody PatientInputDto patientInputDto, @RequestParam String loginUserId) {

		return new ResponseEntity<PatientDto>(patientService.patchPatient(id, patientInputDto, loginUserId),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deletePatientById/{id}")
	public ResponseEntity<Boolean> deletePatient(@PathVariable Long id, @RequestParam String loginUserId) {

		return new ResponseEntity<Boolean>(patientService.deletePatient(id, loginUserId), HttpStatus.OK);

	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			System.out.println("start createAuthenticationToken ");
			System.out.println("request username " + authenticationRequest.getUsername() + " pass "
					+ authenticationRequest.getPassword());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			System.out.println("start createAuthenticationToken inside");
		} catch (BadCredentialsException e) {
			System.out.println("exception " + e.getMessage());
			throw new InvalidUserException("Incorrect Username or password");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		System.out.println("start createAuthenticationToken end");
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
