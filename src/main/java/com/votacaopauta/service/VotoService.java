package com.votacaopauta.service;

import com.votacaopauta.controller.dto.VotoInputDto;

public interface VotoService {
	void votar(Integer pautaId, VotoInputDto votoInputDto);
}
