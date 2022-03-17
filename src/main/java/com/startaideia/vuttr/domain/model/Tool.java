package com.startaideia.vuttr.domain.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Classe responsável pelas atribuições referente a Ferramenta.
 * @author robso
 *
 */
@JsonInclude(Include.NON_NULL)
@Entity
public class Tool {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String title;
	
	@NotBlank
	@Size(max = 50)
	private String link;
	
	@NotBlank
	@Size(max = 255)
	private String description;
	
	@NotEmpty
	private ArrayList<String> listtags; 
	
	/**
	 * Abaixo todos os métodos Getters e Setters da Classe Ferramenta
	 * @return
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<String> getListtags() {
		return listtags;
	}

	public void setListtags(ArrayList<String> listtags) {
		this.listtags = listtags;
	}

	
	

}
