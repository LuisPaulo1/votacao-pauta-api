package com.votacaopauta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.votacaopauta.controller.openapi.SessaoVotacaoControllerOpenApi;
import com.votacaopauta.service.SessaoVotacaoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(path = "/v1/sessoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessaoVotacaoController implements SessaoVotacaoControllerOpenApi {

	@Autowired
	private SessaoVotacaoService service;

	@PostMapping("abrir-sessao-votacao")
	public ResponseEntity<Void> abrirSessaoVotacao(
		@RequestParam(value = "pautaId") Integer pautaId,
		@RequestParam(value = "tempoParaFechamento", defaultValue = "60", required = false) Integer tempoParaFechamento) {
		log.info("Abrindo sessão de votação com pauta de id: {} ", pautaId);
		service.abrirSessaoVotacao(pautaId, tempoParaFechamento);
		log.info("Sessão de votação iniciada com sucesso, o tempo de votação encerra em " + tempoParaFechamento + " segundos.");
		return ResponseEntity.ok().build();
	}

}
