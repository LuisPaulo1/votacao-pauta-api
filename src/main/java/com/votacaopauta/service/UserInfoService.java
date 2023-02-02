package com.votacaopauta.service;

import com.votacaopauta.controller.dto.UserResultDto;

public interface UserInfoService {
	UserResultDto validarCpfDeAssociado(String cpf);
}
