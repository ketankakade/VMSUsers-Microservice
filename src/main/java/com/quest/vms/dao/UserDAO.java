package com.quest.vms.dao;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.quest.vms.dto.UserDTO;
import com.quest.vms.entity.User;

public interface UserDAO {

	public UserDTO addUser(final UserDTO user);

	public UserDTO getUserById(final Integer id);

	public void delete(final User user);

	public UserDTO update(UserDTO userDto);

	public List<UserDTO> listUsers(final String pageNo, final String pageSize, final String sortProperty,
			Direction orderBy);
	
	public List<UserDTO> searchUser(String userCategory, String userName);

}
