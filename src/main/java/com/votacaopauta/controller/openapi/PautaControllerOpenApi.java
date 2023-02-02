package com.votacaopauta.controller.openapi;

import org.springframework.http.ResponseEntity;

import com.votacaopauta.controller.dto.PautaInputDto;
import com.votacaopauta.controller.dto.PautaResultadoDto;
import com.votacaopauta.controller.exception.StandardError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pautas")
public interface PautaControllerOpenApi {

	@ApiOperation("Cadastra uma pauta")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pauta cadastrada."),
		@ApiResponse(code = 400, message = "Requisição inválida.", response = StandardError.class)
	})
	ResponseEntity<PautaResultadoDto> cadastrarPauta(PautaInputDto pautaInputDto);

	@ApiOperation("Consulta o resultado da votação de uma pauta")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Pauta não encontrada.", response = StandardError.class),
		@ApiResponse(code = 404, message = "Sessão de votação da pauta não encontrada.", response = StandardError.class)
	})
	ResponseEntity<PautaResultadoDto> consultarResultadoDaVotacaoNaPauta(Integer pautaId);
}
