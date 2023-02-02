package com.votacaopauta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votacaopauta.model.Pauta;
import com.votacaopauta.model.SessaoVotacao;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Integer> {
	Optional<SessaoVotacao> findByPauta(Pauta pauta);
}
