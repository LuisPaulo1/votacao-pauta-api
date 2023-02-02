package com.votacaopauta.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.votacaopauta.util.Factory;

public class VotoTests {

	@Test
	public void PautaDeveriaTerUmaEstruturaCorreta() {
		SessaoVotacao sessaoVotacao = Factory.createSessaoVotacao();

		Voto voto = new Voto();
		voto.setCpfVotante("987.798.025-83");
		voto.setRespostaDoVoto("Sim");
		voto.setSessaoVotacao(sessaoVotacao);

		Assertions.assertNotNull(voto.getCpfVotante());
		Assertions.assertNotNull(voto.getRespostaDoVoto());
		Assertions.assertNotNull(voto.getSessaoVotacao());
	}
}
