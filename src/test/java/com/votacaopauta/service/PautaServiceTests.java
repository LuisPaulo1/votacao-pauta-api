package com.votacaopauta.service;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.votacaopauta.controller.dto.PautaInputDto;
import com.votacaopauta.controller.dto.PautaResultadoDto;
import com.votacaopauta.model.Pauta;
import com.votacaopauta.repository.PautaRepository;
import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;
import com.votacaopauta.service.impl.PautaServiceImpl;
import com.votacaopauta.util.Factory;

@ExtendWith(SpringExtension.class)
public class PautaServiceTests {

	@InjectMocks
	private PautaServiceImpl service;

	@Mock
	private PautaRepository repository;

	@Spy
	private ModelMapper modelMapper;

	private Integer idExiste;

	private Integer idNaoExiste;

	private Integer pautaIdSemSessaoAberta;

	private PautaInputDto pautaInputDto;

	private Pauta pauta;

	@BeforeEach
	void setUp() throws Exception {
		pautaIdSemSessaoAberta = 2;
		idExiste = 1;
		idNaoExiste = Integer.MAX_VALUE;
		pautaInputDto = Factory.createPautaInputDto();
		pauta = Factory.createPauta();

		when(repository.save(any())).thenReturn(pauta);

		when(repository.findById(idExiste)).thenReturn(Optional.of(pauta));
		when(repository.findById(idNaoExiste)).thenThrow(EntidadeNaoEncontradaException.class);
		when(repository.findById(pautaIdSemSessaoAberta)).thenThrow(EntidadeNaoEncontradaException.class);
	}

	@Test
	public void buscarPautaDeveriaRetornarRecursoQuandoIdExistir() {
		Pauta pauta = service.buscarPauta(idExiste);
		Assertions.assertNotNull(pauta);
	}

	@Test
	public void buscarPautaDeveriaLancarEntidadeNaoEncontradaExceptionQuandoIdNaoExistir() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			service.buscarPauta(idNaoExiste);
		});
	}

	@Test
	public void consultarResultadoDaVotacaoNaPautaDeveriaLancarEntidadeNaoEncontradaExceptionQuandoNaoExistirSessaoAbertaParaIdDaPautaInformada() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			service.consultarResultadoDaVotacaoNaPauta(pautaIdSemSessaoAberta);
		});
	}

	@Test
	public void cadastrarDeveriaSalvarUmRecurso(){
		PautaResultadoDto pauta = service.cadastrar(pautaInputDto);
		Assertions.assertNotNull(pauta);
	}

}
