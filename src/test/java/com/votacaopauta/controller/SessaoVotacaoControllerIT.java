package com.votacaopauta.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class SessaoVotacaoControllerIT {

	@Autowired
	private MockMvc mockMvc;

	private String pautaIdComSessaoAberta;

	private String pautaIdSemSessaoAberta;

	private String pautaIdNaoExiste;

	@BeforeEach
	void setUp() throws Exception {
		pautaIdComSessaoAberta = "1";
		pautaIdSemSessaoAberta = "2";
		pautaIdNaoExiste = String.valueOf(Integer.MAX_VALUE);
	}

	@Test
	public void abrirSessaoVotacaoDeveriaRetornarStatusOkQuandoNaoExistirSessaoAbertaParaIdDaPautaInformada() throws Exception {

		ResultActions result =
			mockMvc.perform(post("/v1/sessoes/abrir-sessao-votacao")
				.param("pautaId", pautaIdSemSessaoAberta)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isOk());
	}

	@Test
	public void abrirSessaoVotacaoDeveriaRetornarStatusConflitQuandoExistirSessaoAbertaParaIdDaPautaInformada() throws Exception {

		ResultActions result =
			mockMvc.perform(post("/v1/sessoes/abrir-sessao-votacao")
				.param("pautaId", pautaIdComSessaoAberta)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isConflict());
	}

	@Test
	public void abrirSessaoVotacaoDeveriaRetornarStatusNotFoundQuandoIdDaPautaNaoExistir() throws Exception {

		ResultActions result =
			mockMvc.perform(post("/v1/sessoes/abrir-sessao-votacao")
					.param("pautaId", pautaIdNaoExiste)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isNotFound());
	}

	@Test
	public void abrirSessaoVotacaoDeveriaRetornarStatusBadRequestQuandoParametroNaoInformado() throws Exception {

		ResultActions result =
			mockMvc.perform(post("/v1/sessoes/abrir-sessao-votacao")
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isBadRequest());
	}

}
