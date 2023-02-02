package com.votacaopauta.service;

import com.votacaopauta.model.Pauta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.votacaopauta.controller.dto.PautaInputDto;
import com.votacaopauta.controller.dto.PautaResultadoDto;
import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;
import com.votacaopauta.util.Factory;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class PautaServiceIT {

	@Autowired
	private PautaService service;

	private Integer idExiste;

	private Integer idNaoExiste;

	private Integer pautaIdSemSessaoAberta;

	private PautaInputDto pautaInputDto;

	@BeforeEach
	void setUp() throws Exception {
		idExiste = 1;
		pautaIdSemSessaoAberta = 2;
		idNaoExiste = Integer.MAX_VALUE;
		pautaInputDto = Factory.createPautaInputDto();
	}

	@Test
	public void buscarPautaDeveriaLancarEntidadeNaoEncontradaExceptionQuandoIdNaoExistir() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			service.buscarPauta(idNaoExiste);
		});
	}

	@Test
	public void buscarPautaDeveriaRetornarRecursoQuandoIdExistir() {
		Pauta pauta = service.buscarPauta(idExiste);
		Assertions.assertNotNull(pauta);
	}

	@Test
	public void cadastrarDeveriaSalvarUmRecurso(){
		PautaResultadoDto pauta = service.cadastrar(pautaInputDto);
		Assertions.assertNotNull(pauta);
	}

	@Test
	public void consultarResultadoDaVotacaoNaPautaDeveriaLancarEntidadeNaoEncontradaExceptionQuandoIdNaoExistir() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			service.consultarResultadoDaVotacaoNaPauta(idNaoExiste);
		});
	}

	@Test
	public void consultarResultadoDaVotacaoNaPautaDeveriaLancarEntidadeNaoEncontradaExceptionQuandoNaoExistirSessaoAbertaParaIdDaPautaInformada() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			service.consultarResultadoDaVotacaoNaPauta(pautaIdSemSessaoAberta);
		});
	}

}
