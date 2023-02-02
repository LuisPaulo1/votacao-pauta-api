package com.votacaopauta.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PautaTests {

	@Test
	public void PautaDeveriaTerUmaEstruturaCorreta() {
		Pauta pauta = new Pauta();
		pauta.setId(1);
		pauta.setNome("Pauta teste");

		Assertions.assertNotNull(pauta.getId());
		Assertions.assertNotNull(pauta.getNome());
	}

}
