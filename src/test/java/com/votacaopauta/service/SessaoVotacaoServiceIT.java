package com.votacaopauta.service;

import com.votacaopauta.service.exception.EntidadeEmUsoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class SessaoVotacaoServiceIT {

	@Autowired
	private SessaoVotacaoService service;

	private Integer pautaIdParaSessaoAberta;

	private Integer pautaIdNaoExiste;

	private Integer tempoParaFechamento;

	@BeforeEach
	void setUp() throws Exception {
		pautaIdParaSessaoAberta = 3;
		pautaIdNaoExiste = Integer.MAX_VALUE;
		tempoParaFechamento = 60;
	}

	@Test
	public void abrirSessaoVotacaoDeveriaLancarEntidadeEmUsoExceptionQuandoExistirSessaoAbertaParaPautaInformada() {
		Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
			service.abrirSessaoVotacao(pautaIdParaSessaoAberta, tempoParaFechamento);
		});
	}

	@Test
	public void abrirSessaoVotacaoDeveriaLancarEntidadeNaoEncontradaExceptionQuandoIdNaoExistir() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			service.abrirSessaoVotacao(pautaIdNaoExiste, tempoParaFechamento);
		});
	}
}
