package com.votacaopauta.service;

import com.votacaopauta.model.Pauta;
import com.votacaopauta.model.SessaoVotacao;


public interface SessaoVotacaoService {
	SessaoVotacao buscarSessaoVotacaoPorPauta(Pauta pauta);
	void abrirSessaoVotacao(Integer pautaId, Integer tempoParaFechamento);
}