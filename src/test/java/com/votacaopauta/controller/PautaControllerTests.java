package com.votacaopauta.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votacaopauta.controller.dto.PautaInputDto;
import com.votacaopauta.controller.dto.PautaResultadoDto;
import com.votacaopauta.service.PautaService;
import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;
import com.votacaopauta.util.Factory;

@WebMvcTest(PautaController.class)
public class PautaControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PautaService service;

	@Autowired
	private ObjectMapper objectMapper;

	private Integer idExiste;

	private Integer idNaoExiste;

	private PautaInputDto pautaInputDto;

	private PautaResultadoDto pautaResultadoDto;

	@BeforeEach
	void setUp() throws Exception {
		idExiste = 1;
		idNaoExiste = Integer.MAX_VALUE;
		pautaInputDto = Factory.createPautaInputDto();
		pautaResultadoDto = Factory.createPautaResultadoDto();

		when(service.cadastrar(any())).thenReturn(pautaResultadoDto);

		when(service.consultarResultadoDaVotacaoNaPauta(idNaoExiste)).thenThrow(EntidadeNaoEncontradaException.class);
		when(service.consultarResultadoDaVotacaoNaPauta(idExiste)).thenReturn(pautaResultadoDto);
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
