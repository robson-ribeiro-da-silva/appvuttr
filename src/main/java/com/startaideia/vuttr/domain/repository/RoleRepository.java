package com.startaideia.vuttr.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startaideia.vuttr.domain.model.Role;

/**
 * Classe que possui os objetos de consultas e conexão ao banco de dados da classe Permissão
 * @author robso
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);

}
