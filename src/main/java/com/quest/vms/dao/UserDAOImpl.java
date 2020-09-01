package com.quest.vms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.quest.vms.dto.UserDTO;
import com.quest.vms.entity.User;
import com.quest.vms.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserDAOImpl implements UserDAO {

	@Autowired
	private UserRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public UserDTO addUser(final UserDTO userDto) {
		log.info("Save user details::User: {}", userDto);
		User user = transformDtoToEntity(userDto);
		user = userRepository.save(user);
		return transformEntityToDto(user);
	}

	@Override
	public void delete(final User user) {
		log.info("Delete user details");
		userRepository.delete(user);
	}

	@Override
	public UserDTO update(UserDTO userDto) {
		log.info("Update user details::Userr: {}", userDto.getUuid());
		Optional<User> user = userRepository.findById(userDto.getUuid());
		if (user == null) {
			return null;
		} else {
			User userToBeUpdated = transformDtoToEntity(userDto);
			userToBeUpdated.setUuid(userDto.getUuid());
			userToBeUpdated.setCreatedTs(user.get().getCreatedTs());
			userToBeUpdated = userRepository.save(userToBeUpdated);
			return transformEntityToDto(userToBeUpdated);
		}
	}

	@Override
	public UserDTO getUserById(final Integer id) {
		UserDTO userDto = null;
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			userDto = transformEntityToDto(user);
		}
		return userDto;
	}

	@Override
	public List<UserDTO> listUsers(String pageNo, String pageSize, String sortProperty, Sort.Direction orderBy) {
		List<UserDTO> userDTOList = new ArrayList<>();
		Pageable paging = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize),
				Sort.by(orderBy, sortProperty));
		Page<User> pagedResult = userRepository.findAll(paging);
		List<User> listedUsers = pagedResult.toList();
		log.info("listedUsers size " + listedUsers.size());
		for (User user : listedUsers) {
			UserDTO userDTO = transformEntityToDto(user);
			userDTOList.add(userDTO);
		}
		return userDTOList;
	}

	@Override
	public List<UserDTO> searchUser(String userCategory, String userName) {

		List<UserDTO> userDTOList = new ArrayList<>();
		List<User> listedUsers = userRepository.findByFirstNameAndUserCategory(userName, userCategory);
		for (User user : listedUsers) {
			UserDTO userDTO = transformEntityToDto(user);
			userDTOList.add(userDTO);
		}
		return userDTOList;
	}

	public User transformDtoToEntity(UserDTO dto) {
		return modelMapper.map(dto, User.class);
	}

	public UserDTO transformEntityToDto(User entity) {

		return modelMapper.map(entity, UserDTO.class);
	}

}
