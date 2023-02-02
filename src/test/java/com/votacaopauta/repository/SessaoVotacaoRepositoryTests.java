package com.votacaopauta.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.votacaopauta.model.Pauta;
import com.votacaopauta.model.SessaoVotacao;
import com.votacaopauta.util.Factory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SessaoVotacaoRepositoryTests {

	@Autowired
	private SessaoVotacaoRepository repository;

	private Integer idExiste;

	private Pauta pauta;

	@BeforeEach
	void setUp() throws Exception {
		idExiste = 1;
		pauta = Factory.createPauta();
	}

	@Test
	public void findByIdDeveriaRetornarSessaoVotacaoQuandoIdExistir() {

		Optional<SessaoVotacao> result = repository.findById(idExiste);

		Assertions.assertTrue(result.isPresent());
	}

	@Test
	public void findByPautaNaoDeveriaRetornarSessaoVotacaoQuandoIdDaPautaNaoExistir() {

		Optional<SessaoVotacao> result = repository.findByPauta(pauta);

		Assertions.assertFalse(result.isPresent());
	}

}
