package com.quest.vms.service;

import javax.validation.Valid;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.JwtResponse;
import com.quest.vms.dto.LoginRequest;

public interface AuthenticateService {

	GenericResponse<JwtResponse> authenticateUser(@Valid LoginRequest loginRequest);

	
}
