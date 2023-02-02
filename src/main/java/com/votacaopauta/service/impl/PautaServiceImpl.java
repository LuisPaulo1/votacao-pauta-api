package com.votacaopauta.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.votacaopauta.controller.dto.PautaInputDto;
import com.votacaopauta.controller.dto.PautaResultadoDto;
import com.votacaopauta.model.Pauta;
import com.votacaopauta.model.SessaoVotacao;
import com.votacaopauta.repository.PautaRepository;
import com.votacaopauta.repository.SessaoVotacaoRepository;
import com.votacaopauta.service.PautaService;
import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;

@Service
public class PautaServiceImpl implements PautaService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@Override
	public Pauta buscarPauta(Integer id){
		Pauta pauta = pautaRepository.findById(id)
			.orElseThrow(() -> new EntidadeNaoEncontradaException("Pauta não encontrada."));
		return pauta;
	}

	@Override
	@Transactional
	public PautaResultadoDto cadastrar(PautaInputDto pautaInputDto) {
		var pauta = modelMapper.map(pautaInputDto, Pauta.class);
		pauta = pautaRepository.save(pauta);
		return modelMapper.map(pauta, PautaResultadoDto.class);
	}

	@Override
	public PautaResultadoDto consultarResultadoDaVotacaoNaPauta(Integer pautaId) {
		Pauta pauta = buscarPauta(pautaId);
		SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findByPauta(pauta)
			.orElseThrow(() -> new EntidadeNaoEncontradaException("Sessão de votação da pauta não encontrada."));
		Map<String, Long> quantidadeDeVotos = sessaoVotacao.obterQuantidadeDeVotos();
		PautaResultadoDto pautaResultadoDto = modelMapper.map(pauta, PautaResultadoDto.class);
		pautaResultadoDto.setQuantidadeDeVotos(quantidadeDeVotos);
		if(LocalDateTime.now().isAfter(sessaoVotacao.getDataHoraFechamento())) {
			calcularResultadoFinal(quantidadeDeVotos, pautaResultadoDto);
		}
		return pautaResultadoDto;
	}

	private void calcularResultadoFinal(Map<String, Long> quantidadeDeVotos, PautaResultadoDto pautaResultadoDto){
		long qtDeVotosSim = quantidadeDeVotos.get("Sim");
		long qtDeVotosNao = quantidadeDeVotos.get("Não");
		String aprovada = "A pauta "+pautaResultadoDto.getNome() + " foi aprovada!";
		String naoAprovada = "A pauta "+pautaResultadoDto.getNome() + " não foi aprovada!";
		String resultado = qtDeVotosSim > qtDeVotosNao ? aprovada : naoAprovada;
		pautaResultadoDto.setResultadoFinal(resultado);
	}

}
