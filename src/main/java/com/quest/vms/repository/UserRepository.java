package com.quest.vms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quest.vms.entity.User;

@Repository
public interface UserRepository
		extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
	
	public List<User> findByFirstNameAndUserCategory( String firstName, String userCategory);

}