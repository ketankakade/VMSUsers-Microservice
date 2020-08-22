package com.quest.vms.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quest.vms.common.utils.ErrorCodes;
import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dao.UserDAO;
import com.quest.vms.dto.UserDTO;
import com.quest.vms.entity.User;
import com.quest.vms.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private UserRepository userRepository;

	@Override
	public GenericResponse<UserDTO> addUser(final UserDTO userDto) {
		GenericResponse<UserDTO> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		log.info("save user.");
		if (userDto != null) {
        //User isPresent = userRepository.findByEmailIgnoreCase(userDto.getEmail());
			//if (isPresent == null) {
			    UserDTO user = userDao.addUser(userDto);
				genericResponse.setStatusCode(HttpStatus.OK.value());
				genericResponse.setMessage("Success");
				genericResponse.setData(Collections.singletonList(user));
			//} else {
				//genericResponse.setMessage("user already registered with email :" + userDto.getEmail());
			//}
		} else {
			genericResponse.setMessage("Failed to the persist User");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<UserDTO> getUserById(final Integer userId) {
		GenericResponse<UserDTO> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		UserDTO userDTO = userDao.getUserById(userId);
		if (userDTO != null) {
			genericResponse.setStatusCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
			genericResponse.setData(Collections.singletonList(userDTO));
		} else {
			genericResponse.setMessage("User not found");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<UserDTO> listUsers(final String pageNo, final String pageSize,
			final String sortProperty, Sort.Direction orderBy) {
		GenericResponse<UserDTO> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		List<UserDTO> listedUsers = userDao.listUsers(pageNo, pageSize, sortProperty, orderBy);
		if (!listedUsers.isEmpty()) {
			genericResponse.setStatusCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
			genericResponse.setData(listedUsers);
		} else {
			genericResponse.setMessage("Users List is empty");
		}
		return genericResponse;
	}
	
	@Override
	public GenericResponse<?> deleteUser(final Integer userId) {
		GenericResponse<?> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE, "BAD_REQUEST",
				null, null);
		Optional<User> userToBeDeleted = userRepository.findById(userId);
		if (userToBeDeleted == null) {
			genericResponse.setMessage("Delete user failed..");
			return genericResponse;
		} else {
			User user = userToBeDeleted.get();
			userDao.delete(user);
			genericResponse.setStatusCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<UserDTO> updateUser(UserDTO userDto) {
		GenericResponse<UserDTO> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		if (userDto == null) {
			genericResponse.setMessage("user data is missing in request");
		} else {
			UserDTO user = userDao.update(userDto);
			if (user == null) {
				genericResponse.setStatusCode(HttpStatus.OK.value());
				genericResponse.setMessage("Success");
				genericResponse.setData(Collections.singletonList(user));
			} else {
				genericResponse.setMessage("user is not found");
			}
		}
		return genericResponse;
	}	
}
