package com.weeklymeal.weeklymeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weeklymeal.weeklymeal.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
}
