package com.votacaopauta.controller;

import javax.validation.Valid;

import com.votacaopauta.controller.dto.VotoInputDto;
import com.votacaopauta.controller.openapi.VotoControllerOpenApi;
import com.votacaopauta.service.VotoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(path = "/v1/votos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VotoController implements VotoControllerOpenApi {

	@Autowired
	private VotoService service;

	@PostMapping("votar")
	public ResponseEntity<Void> votar(@RequestParam Integer pautaId, @RequestBody @Valid VotoInputDto votoInputDto){
		service.votar(pautaId, votoInputDto);
		log.info("Voto registrado com sucesso!");
		return ResponseEntity.ok().build();
	}

}
