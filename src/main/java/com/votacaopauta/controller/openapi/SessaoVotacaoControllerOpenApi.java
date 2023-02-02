package com.votacaopauta.controller.openapi;

import org.springframework.http.ResponseEntity;

import com.votacaopauta.controller.exception.StandardError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Sessões de votação")
public interface SessaoVotacaoControllerOpenApi {

	@ApiOperation("Abre sessão de votação para uma pauta")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Pauta não encontrada.", response = StandardError.class),
		@ApiResponse(code = 409, message = "Já existe uma sessão de votação aberta para a pauta informada.", response = StandardError.class)
	})
	ResponseEntity<Void> abrirSessaoVotacao(Integer pautaId, Integer tempoParaFechamento);
}
