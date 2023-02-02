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
import com.votacaopauta.util.Factory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PautaRepositoryTests {

	@Autowired
	private PautaRepository repository;

	private Integer idExiste;

	private Integer idNaoExiste;

	@BeforeEach
	void setUp() throws Exception {
		idExiste = 1;
		idNaoExiste = Integer.MAX_VALUE;
	}

	@Test
	public void saveDeveriaPersistirComAutoIncrementoQuandoIdNulo() {
		Pauta pauta = Factory.createPauta();
		pauta.setId(null);

		pauta = repository.save(pauta);
		Optional<Pauta> result = repository.findById(pauta.getId());

		Assertions.assertNotNull(pauta.getId());
		Assertions.assertTrue(result.isPresent());
	}

	@Test
	public void findByIdDeveriaRetornarPautaQuandoIdExistir() {

		Optional<Pauta> result = repository.findById(idExiste);

		Assertions.assertTrue(result.isPresent());
	}

	@Test
	public void findByIdNaoDeveriaRetornarPautaQuandoIdNaoExistir() {

		Optional<Pauta> result = repository.findById(idNaoExiste);

		Assertions.assertFalse(result.isPresent());
	}
}
