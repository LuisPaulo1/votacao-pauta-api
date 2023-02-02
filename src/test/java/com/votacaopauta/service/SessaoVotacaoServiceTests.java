package com.votacaopauta.service;

import com.votacaopauta.model.Pauta;
import com.votacaopauta.repository.SessaoVotacaoRepository;
import com.votacaopauta.service.exception.EntidadeNaoEncontradaException;
import com.votacaopauta.service.impl.SessaoVotacaoServiceImpl;
import com.votacaopauta.util.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SessaoVotacaoServiceTests {

	@InjectMocks
	private SessaoVotacaoServiceImpl service;

	@Mock
	private SessaoVotacaoRepository repository;

	private Pauta pauta;

	@BeforeEach
	void setUp() throws Exception {
		pauta = Factory.createPauta();
		when(repository.findByPauta(pauta)).thenThrow(EntidadeNaoEncontradaException.class);
	}

	@Test
	public void buscarSessaoVotacaoPorPautaDeveriaLancarEntidadeNaoEncontradaExceptionQuandoIdDaPautaNaoExistir() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			service.buscarSessaoVotacaoPorPauta(pauta);
		});
	}

}
