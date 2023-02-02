package com.votacaopauta.service;

import com.votacaopauta.service.exception.NegocioException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.votacaopauta.controller.dto.VotoInputDto;
import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;
import com.votacaopauta.util.Factory;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class VotoServiceIT {

	@Autowired
	private VotoService service;

	private Integer pautaIdParaSessaoNaoAberta;

	private Integer pautaIdParaSessaoEncerrada;

	private Integer pautaIdParaVotoJaRegistrado;

	private Integer idNaoExiste;

	private VotoInputDto votoInputDto;

	@BeforeEach
	void setUp() throws Exception {
		pautaIdParaVotoJaRegistrado = 1;
		pautaIdParaSessaoNaoAberta = 2;
		pautaIdParaSessaoEncerrada = 3;
		idNaoExiste = Integer.MAX_VALUE;
		votoInputDto = Factory.createVotoInputDto();
	}

	@Test
	public void votarDeveriaLancarEntidadeNaoEncontradaExceptionQuandoIdNaoExistir() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			service.votar(idNaoExiste, votoInputDto);
		});
	}

	@Test
	public void votarDeveriaLancarEntidadeNaoEncontradaExceptionQuandoSessaoNaoEstiverAbertaParaPautaInformada() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			service.votar(pautaIdParaSessaoNaoAberta, votoInputDto);
		});
	}

	@Test
	public void votarDeveriaLancarNegocioExceptionQuandoSessaoEstiverEncerradaParaPautaInformada() {
		Assertions.assertThrows(NegocioException.class, () -> {
			service.votar(pautaIdParaSessaoEncerrada, votoInputDto);
		});
	}

	@Test
	public void votarDeveriaLancarNegocioExceptionQuandoCpfDeAssociadoTiverVotoRegistrado() {
		Assertions.assertThrows(NegocioException.class, () -> {
			service.votar(pautaIdParaVotoJaRegistrado, votoInputDto);
		});
	}

}
