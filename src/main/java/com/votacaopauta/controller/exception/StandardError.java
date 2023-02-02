package com.votacaopauta.controller.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime dateTime;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
}