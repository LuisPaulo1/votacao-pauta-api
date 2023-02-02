package com.votacaopauta.controller.exception;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;

import com.votacaopauta.service.exception.EntidadeEmUsoException;
import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;
import com.votacaopauta.service.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<StandardError> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setDateTime(LocalDateTime.now());
		err.setStatus(status.value());
		err.setError("Entidade não encontrada");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<StandardError> handleNegocioException(NegocioException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setDateTime(LocalDateTime.now());
		err.setStatus(status.value());
		err.setError("Erro de negócio");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<StandardError> handleEntidadeEmUsoException(EntidadeEmUsoException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError();
		err.setDateTime(LocalDateTime.now());
		err.setStatus(status.value());
		err.setError("Erro de conflito de dados");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<StandardError> handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setDateTime(LocalDateTime.now());
		err.setStatus(status.value());
		err.setError("Parâmetro obrigatório");
		err.setMessage(String.format("O parâmetro de URL '%s' é obrigatório. ", e.getParameterName()));
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		String mensagem = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. "
				+"Corrija e informe um valor compatível com o tipo %s.", e.getName(), e.getValue(), e.getRequiredType().getSimpleName());
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setDateTime(LocalDateTime.now());
		err.setStatus(status.value());
		err.setError("Parâmetro inválido");
		err.setMessage(mensagem);
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError err = new ValidationError();
		err.setDateTime(LocalDateTime.now());
		err.setStatus(status.value());
		err.setError("Erro de validação de dados");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<StandardError> handleResourceAccessException(ResourceAccessException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
		StandardError err = new StandardError();
		err.setDateTime(LocalDateTime.now());
		err.setStatus(status.value());
		err.setError("Serviço indisponivel.");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> handleErroDeSistema(Exception e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError();
		err.setDateTime(LocalDateTime.now());
		err.setStatus(status.value());
		err.setError("Erro de sistema");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}