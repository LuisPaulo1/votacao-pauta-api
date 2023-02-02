package com.votacaopauta.service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntidadeNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}
}
