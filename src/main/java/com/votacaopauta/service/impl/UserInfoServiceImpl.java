package com.votacaopauta.service.impl;

import com.votacaopauta.client.UserInfoClient;
import com.votacaopauta.controller.dto.UserResultDto;
import com.votacaopauta.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoClient userInfoClient;

	@Override
	public UserResultDto validarCpfDeAssociado(String cpf) {
		UserResultDto userResultDto = userInfoClient.validar(cpf);
		if(userResultDto == null) {
			userResultDto = new UserResultDto("UNABLE_TO_VOTE");
		}
		return userResultDto;
	}

}
