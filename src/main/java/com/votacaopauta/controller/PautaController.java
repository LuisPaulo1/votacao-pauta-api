package com.votacaopauta.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.votacaopauta.controller.dto.PautaInputDto;
import com.votacaopauta.controller.dto.PautaResultadoDto;
import com.votacaopauta.controller.openapi.PautaControllerOpenApi;
import com.votacaopauta.service.PautaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(path = "/v1/pautas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PautaController implements PautaControllerOpenApi {

	@Autowired
	private PautaService service;

	@PostMapping("cadastrar")
	public ResponseEntity<PautaResultadoDto> cadastrarPauta(@RequestBody @Valid PautaInputDto pautaInputDto){
		log.info("Cadastrando pauta...");
		PautaResultadoDto pautaResultadoDto = service.cadastrar(pautaInputDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pautaResultadoDto.getId()).toUri();
		log.info("Pauta cadastrada com sucesso!");
		return ResponseEntity.created(uri).body(pautaResultadoDto);
	}

	@GetMapping("consultar/{pautaId}")
	public ResponseEntity<PautaResultadoDto> consultarResultadoDaVotacaoNaPauta(@PathVariable Integer pautaId){
		log.info("Consultando resultado das votações na pauta...");
		PautaResultadoDto pautaResultadoDto = service.consultarResultadoDaVotacaoNaPauta(pautaId);
		return ResponseEntity.ok(pautaResultadoDto);
	}

}
