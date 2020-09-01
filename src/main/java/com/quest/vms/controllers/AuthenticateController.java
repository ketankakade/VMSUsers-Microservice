package com.quest.vms.controllers;

import static com.quest.vms.common.utils.VmsConstants.AUTH_URL_PATH;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.JwtResponse;
import com.quest.vms.dto.LoginRequest;
import com.quest.vms.security.JwtUtils;
import com.quest.vms.security.UserDetailsImpl;
import com.quest.vms.service.AuthenticateService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/" + AUTH_URL_PATH)
@Api(value = "Authenticate controller", description = "")
@Slf4j
public class AuthenticateController {

	@Autowired AuthenticateService authenticateService;

	@PostMapping("/signin")
	public GenericResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		log.info("auth user ");
		GenericResponse<JwtResponse> filerListUserGenericResponse = null;
		try {
			filerListUserGenericResponse = authenticateService.authenticateUser(loginRequest);
			return filerListUserGenericResponse;
		} catch (Exception e) {
			log.error(e.getMessage());
			return filerListUserGenericResponse;
		}
		
	}

}
