package com.startaideia.vuttr.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

/**
 * Classe responsável pelas atribuições referente as Permissões.
 * @author robso
 * 
 * Implementa a claase GrantedAuthority
 */
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;

    /**
     * Construtor vazio da classe 
     */
    public Role() { }
    
    /**
     * Construtor da Classe que recebe um parâmetro
     * @param name - uma String referente ao nome da permissão
     */
    public Role(String name) {
        this.name = name;
    }
    

    /**
     * subscrição do metódo getAuthority() da classe GrantedAuthority
     */
    @Override
    public String getAuthority() {
        return  this.name;
    }

}
