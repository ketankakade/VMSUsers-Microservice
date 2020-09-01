package com.quest.vms.service;

import java.util.Collections;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.quest.vms.common.utils.ErrorCodes;
import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.JwtResponse;
import com.quest.vms.dto.LoginRequest;
import com.quest.vms.entity.SignIn;
import com.quest.vms.repository.AuthenticationRepository;
import com.quest.vms.security.JwtUtils;
import com.quest.vms.security.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {
	@Autowired
	AuthenticationManager authenticationManager;;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	AuthenticationRepository authenticationRepository;

	@Override
	public GenericResponse<JwtResponse> authenticateUser(@Valid LoginRequest loginRequest) {
		GenericResponse<JwtResponse> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		Optional<SignIn> signIn = authenticationRepository.findByUsername(loginRequest.getUsername());
		if (signIn != null) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			JwtResponse res = new JwtResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
					jwt);
			genericResponse.setStatusCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
			genericResponse.setData(Collections.singletonList(res));
		} else {
			log.info("Username is incorrect........");
			genericResponse.setMessage("Username is incorrect");
		}
		return genericResponse;
	}

}
