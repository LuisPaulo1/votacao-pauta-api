package com.votacaopauta.controller.openapi;

import org.springframework.http.ResponseEntity;

import com.votacaopauta.controller.dto.VotoInputDto;
import com.votacaopauta.controller.exception.StandardError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Votos")
public interface VotoControllerOpenApi {

	@ApiOperation("Registra o voto na pauta")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Pauta não encontrada.", response = StandardError.class),
		@ApiResponse(code = 404, message = "Sessão de votação da pauta não encontrada.", response = StandardError.class),
		@ApiResponse(code = 400, message = "A sessão já foi encerrada.", response = StandardError.class),
		@ApiResponse(code = 400, message = "O associado já tem voto registrado. É permitido somente um voto por associado.", response = StandardError.class)
	})
	ResponseEntity<Void> votar(Integer pautaId, VotoInputDto votoInputDto);

}
