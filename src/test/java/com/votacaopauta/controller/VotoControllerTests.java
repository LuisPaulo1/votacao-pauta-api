package com.votacaopauta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votacaopauta.controller.dto.VotoInputDto;
import com.votacaopauta.service.VotoService;
import com.votacaopauta.util.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VotoController.class)
public class VotoControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VotoService service;

	@Autowired
	private ObjectMapper objectMapper;

	private Integer pautaIdParaSessaoAbertaSemVotoDeAssociado;

	private VotoInputDto votoInputDto;


	@BeforeEach
	void setUp() throws Exception {
		pautaIdParaSessaoAbertaSemVotoDeAssociado = 4;
		votoInputDto = Factory.createVotoInputDto();

		doNothing().when(service).votar(pautaIdParaSessaoAbertaSemVotoDeAssociado, votoInputDto);
	}

	@Test
	void votarDeveriaRetornarStatusOKQuandoPautaTemSessaoAbertaSemVotoDeAssociado() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdParaSessaoAbertaSemVotoDeAssociado.toString())
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isOk());
	}

}
