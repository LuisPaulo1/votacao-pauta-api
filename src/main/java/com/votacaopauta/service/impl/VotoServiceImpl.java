package com.votacaopauta.service.impl;

import java.time.LocalDateTime;

import com.votacaopauta.controller.dto.VotoInputDto;
import com.votacaopauta.model.Pauta;
import com.votacaopauta.model.SessaoVotacao;
import com.votacaopauta.model.Voto;
import com.votacaopauta.repository.VotoRepository;
import com.votacaopauta.service.PautaService;
import com.votacaopauta.service.SessaoVotacaoService;
import com.votacaopauta.service.VotoService;
import com.votacaopauta.service.exception.NegocioException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotoServiceImpl implements VotoService {

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private PautaService pautaService;

	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public void votar(Integer pautaId, VotoInputDto votoInputDto) {
		Pauta pauta = pautaService.buscarPauta(pautaId);
		SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscarSessaoVotacaoPorPauta(pauta);

		if(LocalDateTime.now().isAfter(sessaoVotacao.getDataHoraFechamento())){
			throw new NegocioException("A sessão já foi encerrada!");
		}

		if(votoRepository.existsBySessaoVotacaoAndCpfVotante(sessaoVotacao, votoInputDto.getCpfVotante())){
			throw new NegocioException("O associado já tem voto registrado. É permitido somente um voto por associado!");
		}

		var voto = modelMapper.map(votoInputDto, Voto.class);
		voto.setSessaoVotacao(sessaoVotacao);
		votoRepository.save(voto);
	}

}
