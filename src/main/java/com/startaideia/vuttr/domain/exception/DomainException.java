package com.startaideia.vuttr.domain.exception;

/**
 * Classe para tratamento de erro de dominio
 * @author robso
 * Estende a classe RuntimeException
 */
public class DomainException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DomainException(String message) {
		super(message);
	}

}
