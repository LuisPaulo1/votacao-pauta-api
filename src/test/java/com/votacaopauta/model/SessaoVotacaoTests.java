package com.votacaopauta.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.votacaopauta.util.Factory;

public class SessaoVotacaoTests {

	@Test
	public void PautaDeveriaTerUmaEstruturaCorreta() {
		Pauta pauta = Factory.createPauta();
		Voto voto = Factory.createVoto();
		Set<Voto> votos = new HashSet<>();
		votos.add(voto);

		SessaoVotacao sessaoVotacao = new SessaoVotacao();
		sessaoVotacao.setId(1);
		sessaoVotacao.setDataHoraAbertura(LocalDateTime.now());
		sessaoVotacao.setDataHoraFechamento(LocalDateTime.now().plusSeconds(120));
		sessaoVotacao.setPauta(pauta);
		sessaoVotacao.setVotos(votos);

		Assertions.assertNotNull(sessaoVotacao.getId());
		Assertions.assertNotNull(sessaoVotacao.getDataHoraAbertura());
		Assertions.assertNotNull(sessaoVotacao.getDataHoraFechamento());
		Assertions.assertNotNull(sessaoVotacao.getPauta());
		Assertions.assertFalse(sessaoVotacao.getVotos().isEmpty());
	}

}
