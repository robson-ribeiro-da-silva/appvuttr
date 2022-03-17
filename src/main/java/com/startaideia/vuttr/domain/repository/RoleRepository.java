package com.startaideia.vuttr.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startaideia.vuttr.domain.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);

}
