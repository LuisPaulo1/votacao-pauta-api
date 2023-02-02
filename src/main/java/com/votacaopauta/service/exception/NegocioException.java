package com.votacaopauta.service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NegocioException(String msg) {
		super(msg);
	}
}
