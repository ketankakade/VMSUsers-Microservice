package com.quest.vms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.quest.vms.entity.User;

@Repository
public interface UserRepository
		extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {

}