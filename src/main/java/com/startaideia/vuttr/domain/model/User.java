package com.startaideia.vuttr.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe responsável pelas atribuições referente ao Usuario.
 * @author robso
 *
 */
@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotBlank
	private String name;
    
	@Column(unique = true)
    private String email;
    
	@JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns= {@JoinColumn(name="user_id")},
            inverseJoinColumns= {@JoinColumn(name="role_id")})
    private List<Role> roles;  // referente as permissões que o usuario tem
    
    /**
	 * Abaixo todos os métodos Construtores da Classe Usuario
	 * @return
	 */

    public User() { }

    /**
     * Construtor da Classe que recebe dois parâmetros
     * @param name - uma String referente ao nome
     * @param email - uma String referente ao email
     */
    public User(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }
    /**
     * Construtor da Classe que recebe um parâmetro do tipo usuario
     * @param user - tipo usuario
     */
    public User(User user) {
        super();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.id = user.getId();
    }
	/**
	 * Construtor da Classe que recebe quatro parâmetros
     * @param name - uma String referente ao nome
     * @param email - uma String referente ao email
	 * @param password - uma String referente a senha de acesso
	 * @param roles - Lista de permissões do usuário
	 */
    public User(String name, String email, String password, List<Role> roles) {
        super();
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    /**
	 * Abaixo todos os métodos Getters e Setters da Classe Usuario
	 * @return
	 */
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
