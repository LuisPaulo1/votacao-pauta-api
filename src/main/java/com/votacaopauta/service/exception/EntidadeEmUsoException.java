package com.votacaopauta.service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntidadeEmUsoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String msg) {
		super(msg);
	}
}
