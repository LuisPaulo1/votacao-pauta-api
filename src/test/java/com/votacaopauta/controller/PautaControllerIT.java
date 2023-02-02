package com.votacaopauta.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votacaopauta.controller.dto.PautaInputDto;
import com.votacaopauta.util.Factory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class PautaControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Integer idExiste;

	private Integer idNaoExiste;

	private PautaInputDto pautaInputDto;

	@BeforeEach
	void setUp() throws Exception {
		idNaoExiste = Integer.MAX_VALUE;
		idExiste = 1;
		pautaInputDto = Factory.createPautaInputDto();
	}

	@Test
	public void cadastrarPautaDeveriaRetornarStatusCreatedQuandoInserirRecursoInformandoCampoNome() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(pautaInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/pautas/cadastrar")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isCreated());
	}

	@Test
	public void cadastrarPautaDeveriaRetornarStatusBadRequestQuandoInserirRecursoSemInformarCampoNome() throws Exception {

		PautaInputDto pauta = new PautaInputDto("");
		String jsonBody = objectMapper.writeValueAsString(pauta);

		ResultActions result =
			mockMvc.perform(post("/v1/pautas/cadastrar")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isBadRequest());
	}

	@Test
	public void consultarResultadoDaVotacaoNaPautaDeveriaRetornarStatusOkQuandoIdDaPautaExistir() throws Exception {

		ResultActions result =
			mockMvc.perform(get("/v1/pautas/consultar/{id}", idExiste)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isOk());
	}

	@Test
	public void consultarResultadoDaVotacaoNaPautaDeveriaRetornarStatusNotFoundQuandoIdDaPautaNaoExistir() throws Exception {

		ResultActions result =
			mockMvc.perform(get("/v1/pautas/consultar/{id}", idNaoExiste)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isNotFound());
	}

}
