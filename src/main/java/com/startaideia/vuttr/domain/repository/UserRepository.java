package com.startaideia.vuttr.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startaideia.vuttr.domain.model.User;

/**
 * Classe que possui os objetos de consultas e conexão ao banco de dados da classe Usuario
 * @author robso
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{

	 User findByEmail(String email);
}
