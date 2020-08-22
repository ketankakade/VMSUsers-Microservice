package com.quest.vms.controllers;

import static com.quest.vms.common.utils.VmsConstants.ID;
import static com.quest.vms.common.utils.VmsConstants.USER;
import static com.quest.vms.common.utils.VmsConstants.USERS_URL_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.UserDTO;
import com.quest.vms.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/" + USERS_URL_PATH)
@Api(value = "Visitor Management System", description = "Operations pertaining to Visitor Management System")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Add a User to system")
	@PostMapping(USER)
	public GenericResponse<UserDTO> addUser(@RequestBody UserDTO user) {
		GenericResponse<UserDTO> createUserGenericResponse = null;
		try {
			createUserGenericResponse = userService.addUser(user);
			return createUserGenericResponse;
		} catch (Exception e) {
			return createUserGenericResponse;
		}
	}

	@ApiOperation(value = "Get User by Id")
	@GetMapping(USER + "/{" + ID + "}")
	public GenericResponse<UserDTO> getUserById(@PathVariable(value = ID) Integer id) {
		GenericResponse<UserDTO> getUserGenericResponse = null;
		try {
			getUserGenericResponse = userService.getUserById(id);
			return getUserGenericResponse;
		} catch (Exception e) {
			return getUserGenericResponse;
		}
	}

	@ApiOperation(value = "Get All Users from system")
	@GetMapping(USER)
	public GenericResponse<UserDTO> listUsers(
			@RequestParam(value = "index", defaultValue = "0", required = false) String index,
			@RequestParam(value = "size", defaultValue = "10", required = false) String size,
			@RequestParam(value = "sortBy", defaultValue = "firstName", required = false) String sortBy,
			@RequestParam(value = "orderBy", defaultValue = "ASC", required = false) Sort.Direction orderBy) {
		log.info("list user");
		GenericResponse<UserDTO> listUserGenericResponse = null;
		try {
			listUserGenericResponse = userService.listUsers(index, size, sortBy, orderBy);
			return listUserGenericResponse;
		} catch (Exception e) {
			log.error("error" + e.getMessage());
			return listUserGenericResponse;
		}
	}

	
	@ApiOperation(value = "Delete User from system")
	@DeleteMapping(USER + "/{id}")
	public GenericResponse<?> deleteUser(@PathVariable(value = "id") Integer userId) {
		GenericResponse<?> deleteUserGenericResponse = null;
		try {
			deleteUserGenericResponse = userService.deleteUser(userId);
			return deleteUserGenericResponse;
		} catch (Exception e) {
			log.error(e.getMessage());
			return deleteUserGenericResponse;
		}
	}

	@ApiOperation(value = "Update User details")
	@PutMapping(USER)
	public GenericResponse<UserDTO> updateUser(@RequestBody UserDTO user) {
		GenericResponse<UserDTO> updateUserGenericResponse = null;
		try {
			updateUserGenericResponse = userService.updateUser(user);
			return updateUserGenericResponse;
		} catch (Exception e) {
			log.error(e.getMessage());
			return updateUserGenericResponse;
		}
	}	
}