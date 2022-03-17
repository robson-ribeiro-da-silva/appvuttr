package com.startaideia.vuttr.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startaideia.vuttr.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	 User findByEmail(String email);
}
