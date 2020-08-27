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
	
	
	@Query("select u from User u where "
			+ " ( u.firstName=?1 or ?1 is NULL or ?1 = '' )  and "
			+ " ( u.userCategory=?2 or ?2 is NULL or ?2 = '' ) ")
	public List<User> findByFirstNameAndUserCategory(@Param("firstName") String firstName, @Param("userCategory") String userCategory);

}