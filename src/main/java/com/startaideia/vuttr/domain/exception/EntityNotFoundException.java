package com.startaideia.vuttr.domain.exception;

/**
 * Classe para tratamento de erro caso a Entidade nao seja encontrada
 * @author robso
 * Estende a classe DomainException
 */
public class EntityNotFoundException extends DomainException{
	
	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(String message) {
		super(message);
	}

}
