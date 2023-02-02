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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votacaopauta.controller.dto.VotoInputDto;
import com.votacaopauta.util.Factory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class VotoControllerIT {

	@Autowired
	private MockMvc mockMvc;

	private String pautaIdParaVotoJaRegistrado;

	private String pautaIdNaoExiste;

	private String pautaIdParaSessaoEncerrada;

	private String pautaIdParaSessaoNaoAberta;

	private String pautaIdParaSessaoAbertaSemVotoDeAssociado;

	@Autowired
	private ObjectMapper objectMapper;

	private VotoInputDto votoInputDto;

	@BeforeEach
	void setUp() throws Exception {
		pautaIdParaVotoJaRegistrado = "1";
		pautaIdParaSessaoNaoAberta = "2";
		pautaIdParaSessaoEncerrada = "3";
		pautaIdParaSessaoAbertaSemVotoDeAssociado = "4";
		pautaIdNaoExiste = String.valueOf(Integer.MAX_VALUE);
		votoInputDto = Factory.createVotoInputDto();
	}

	@Test
	public void votarDeveriaRetornarStatusOKQuandoPautaTemSessaoAbertaSemVotoDeAssociado() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdParaSessaoAbertaSemVotoDeAssociado)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isOk());
	}

	@Test
	public void votarDeveriaRetornarStatusNotFoundQuandoPautaNaoTemSessaoAberta() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdParaSessaoNaoAberta)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isNotFound());
	}

	@Test
	public void votarDeveriaRetornarStatusNotFoundQuandoIdDaPautaNaoExistir() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdNaoExiste)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isNotFound());
	}

	@Test
	public void votarDeveriaRetornarStatusBadRequestQuandoSessaoDaPautaJaEstiverEncerrada() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdParaSessaoEncerrada)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isBadRequest());
	}

	@Test
	public void votarDeveriaRetornarStatusBadRequestQuandoAssociadoJaTemVotoRegistrado() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdParaVotoJaRegistrado)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isBadRequest());
	}

	@Test
	public void votarDeveriaRetornarStatusBadRequestQuandoParametroNaoForInformado() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isBadRequest());
	}

	@Test
	public void votarDeveriaRetornarStatusBadRequestQuandoParametroForInvalido() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", "a")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isBadRequest());
	}

	@Test
	public void votarDeveriaRetornarStatusBadRequestQuandoVotoInputDtoNaoInformaCpf() throws Exception {
		votoInputDto.setCpfVotante("");
		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdParaSessaoAbertaSemVotoDeAssociado)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isBadRequest());
	}

	@Test
	public void votarDeveriaRetornarStatusBadRequestQuandoVotoInputDtoTemCpfInvalido() throws Exception {
		votoInputDto.setCpfVotante("123456");
		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdParaSessaoAbertaSemVotoDeAssociado)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isBadRequest());
	}

	@Test
	public void votarDeveriaRetornarStatusBadRequestQuandoVotoInputDtoNaoInformaRespostaDoVoto() throws Exception {
		votoInputDto.setRespostaDoVoto("");
		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdParaSessaoAbertaSemVotoDeAssociado)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isBadRequest());
	}

	@Test
	public void votarDeveriaRetornarStatusBadRequestQuandoVotoInputDtoTemRespostaDoVotoInvalido() throws Exception {
		votoInputDto.setRespostaDoVoto("SIM");
		String jsonBody = objectMapper.writeValueAsString(votoInputDto);

		ResultActions result =
			mockMvc.perform(post("/v1/votos/votar")
				.param("pautaId", pautaIdParaSessaoAbertaSemVotoDeAssociado)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpectAll(status().isBadRequest());
	}

}
