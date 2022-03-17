package com.startaideia.vuttr.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.startaideia.vuttr.domain.model.Tool;

/**
 * Classe que possui os objetos de consultas e conexão ao banco de dados da classe Ferramenta
 * @author robso
 *
 */
@Repository
public interface ToolRepository extends JpaRepository<Tool, Long>{
	
	List<Tool> findByTitleContaining(String title);
	
}
