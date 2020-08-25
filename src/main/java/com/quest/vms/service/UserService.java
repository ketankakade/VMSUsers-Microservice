package com.quest.vms.service;

import org.springframework.data.domain.Sort.Direction;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.UserDTO;

public interface UserService {

	public GenericResponse<UserDTO> addUser(final UserDTO user);

	public GenericResponse<UserDTO> getUserById(final Integer id);

	public GenericResponse<?> deleteUser(final Integer id);

	public GenericResponse<UserDTO> updateUser(UserDTO user);

	public GenericResponse<UserDTO> listUsers(String index, String size, String sortBy, Direction orderBy);
	
	public GenericResponse<UserDTO> searchUser(String userCategory, String userName);
	
	}
