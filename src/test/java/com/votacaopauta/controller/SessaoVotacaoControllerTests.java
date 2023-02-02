package com.votacaopauta.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.votacaopauta.service.exception.EntidadeEmUsoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.votacaopauta.service.SessaoVotacaoService;
import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;

@WebMvcTest(SessaoVotacaoController.class)
public class SessaoVotacaoControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SessaoVotacaoService service;

	private Integer pautaIdNaoExiste;

	private Integer pautaIdComSessaoAberta;

	private Integer pautaIdSemSessaoAberta;

	private Integer tempoParaFechamento;

	@BeforeEach
	void setUp() throws Exception {
		pautaIdComSessaoAberta = 1;
		pautaIdSemSessaoAberta = 2;
		pautaIdNaoExiste = Integer.MAX_VALUE;
		tempoParaFechamento = 60;

		doNothing().when(service).abrirSessaoVotacao(pautaIdSemSessaoAberta, tempoParaFechamento);

		doThrow(EntidadeNaoEncontradaException.class).when(service).abrirSessaoVotacao(pautaIdNaoExiste, tempoParaFechamento);
		doThrow(EntidadeEmUsoException.class).when(service).abrirSessaoVotacao(pautaIdComSessaoAberta, tempoParaFechamento);
	}

	@Test
	public void abrirSessaoVotacaoDeveriaRetornarStatusOkQuandoNaoExistirSessaoAbertaParaIdDaPautaInformada() throws Exception {

		ResultActions result =
			mockMvc.perform(post("/v1/sessoes/abrir-sessao-votacao")
				.param("pautaId", pautaIdSemSessaoAberta.toString())
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isOk());
	}

	@Test
	public void abrirSessaoVotacaoDeveriaRetornarStatusConflitQuandoExistirSessaoAbertaParaIdDaPautaInformada() throws Exception {

		ResultActions result =
			mockMvc.perform(post("/v1/sessoes/abrir-sessao-votacao")
				.param("pautaId", pautaIdComSessaoAberta.toString())
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isConflict());
	}

	@Test
	public void abrirSessaoVotacaoDeveriaRetornarStatusNotFoundQuandoIdDaPautaNaoExistir() throws Exception {

		ResultActions result =
			mockMvc.perform(post("/v1/sessoes/abrir-sessao-votacao")
				.param("pautaId", pautaIdNaoExiste.toString())
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isNotFound());
	}

}
