package com.votacaopauta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votacaopauta.model.SessaoVotacao;
import com.votacaopauta.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, String> {
	Boolean existsBySessaoVotacaoAndCpfVotante(SessaoVotacao sessaoVotacao, String cpfVotante);
}
