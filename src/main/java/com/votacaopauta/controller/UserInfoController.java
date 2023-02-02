package com.votacaopauta.controller;

import com.votacaopauta.controller.dto.UserResultDto;
import com.votacaopauta.controller.openapi.UserInfoControllerOpenApi;
import com.votacaopauta.service.UserInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(path = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserInfoController implements UserInfoControllerOpenApi {

	@Autowired
	private UserInfoService service;

	@GetMapping("{cpf}")
	public ResponseEntity<UserResultDto> validar(@PathVariable String cpf){
		log.info("Validando CPF de associado...");
		UserResultDto userResultDto = service.validarCpfDeAssociado(cpf);
		if(userResultDto.getStatus().startsWith("U")){
			log.info("CPF do associado é inválido!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResultDto);
		}
		log.info("CPF do associado é valido!");
		return ResponseEntity.ok(userResultDto);
	}

}
