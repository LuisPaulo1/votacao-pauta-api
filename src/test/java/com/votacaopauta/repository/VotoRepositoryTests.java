package com.votacaopauta.repository;

import java.util.Optional;

import com.votacaopauta.model.Voto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.votacaopauta.model.SessaoVotacao;
import com.votacaopauta.util.Factory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class VotoRepositoryTests {

	@Autowired
	private VotoRepository repository;

	private SessaoVotacao sessaoVotacao;

	private String cpfComVotoRegistrado;

	private String cpfSemVotoRegistrado;

	@BeforeEach
	void setUp() throws Exception {
		sessaoVotacao = Factory.createSessaoVotacao();
		cpfComVotoRegistrado = "751.278.628-08";
		cpfSemVotoRegistrado = "987.798.025-83";
	}

	@Test
	public void findByIdDeveriaRetornarRecursoQuandoCpfExistir() {

		Optional<Voto> result = repository.findById(cpfComVotoRegistrado);

		Assertions.assertTrue(result.isPresent());
	}

	@Test
	public void existsBySessaoVotacaoAndCpfVotanteDeveriaRetornarFalsoQuandoCpfNaoExistir() {

		Boolean result = repository.existsBySessaoVotacaoAndCpfVotante(sessaoVotacao, cpfSemVotoRegistrado);

		Assertions.assertFalse(result);
	}

}
