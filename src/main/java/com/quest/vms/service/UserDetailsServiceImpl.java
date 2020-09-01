package com.quest.vms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.RoleDTO;
import com.quest.vms.entity.SignIn;
import com.quest.vms.entity.User;
import com.quest.vms.exception.ServiceException;
import com.quest.vms.repository.AuthenticationRepository;
import com.quest.vms.repository.UserRepository;
import com.quest.vms.security.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${getRolesUrl}")
	String getRolesUrl;
	
	private RestTemplate restTemplate;

	public UserDetailsServiceImpl() {
		restTemplate = new RestTemplate();
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SignIn signIn = authenticationRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		User user = userRepository.findByPrimaryEmailId(signIn.getEmail());
		if(user == null) {
			throw new ServiceException("User Not found");
		}else {log.info("loadUserByUsername 2" + user.getPrimaryEmailId());
		//@SuppressWarnings("unchecked")
		RoleDTO getVisitorGenericRes = restTemplate.getForObject(getRolesUrl,
				RoleDTO.class, user.getUserCategory());
		//log.info(getVisitorGenericRes.getData().get(0).getRoleName());
		return UserDetailsImpl.build(signIn, getVisitorGenericRes.getRoleName());
		}
		
	}

}
