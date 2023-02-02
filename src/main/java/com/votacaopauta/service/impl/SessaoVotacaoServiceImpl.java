package com.votacaopauta.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import com.votacaopauta.model.Pauta;
import com.votacaopauta.model.SessaoVotacao;
import com.votacaopauta.repository.SessaoVotacaoRepository;
import com.votacaopauta.service.PautaService;
import com.votacaopauta.service.SessaoVotacaoService;
import com.votacaopauta.service.exception.EntidadeEmUsoException;
import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {

	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@Autowired
	private PautaService pautaService;

	@Override
	public SessaoVotacao buscarSessaoVotacaoPorPauta(Pauta pauta) {
		SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findByPauta(pauta)
			.orElseThrow(() -> new EntidadeNaoEncontradaException("Sessão de votação não aberta para a pauta informada."));
		return sessaoVotacao;
	}

	@Override
	@Transactional
	public void abrirSessaoVotacao(Integer pautaId, Integer tempoParaFechamento) {
		Pauta pauta = pautaService.buscarPauta(pautaId);
		Optional<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.findByPauta(pauta);
		if(sessaoVotacao.isPresent()) {
			throw new EntidadeEmUsoException("Já existe uma sessão de votação aberta para a pauta informada.");
		}
		criarSessaoVotacao(pauta, tempoParaFechamento);
	}

	private void criarSessaoVotacao(Pauta pauta, Integer tempoParaFechamento) {
		SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
			.dataHoraAbertura(LocalDateTime.now())
			.dataHoraFechamento(LocalDateTime.now().plusSeconds(tempoParaFechamento))
			.pauta(pauta)
			.build();
		sessaoVotacaoRepository.save(sessaoVotacao);
	}

}
