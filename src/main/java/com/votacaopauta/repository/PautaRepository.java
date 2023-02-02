package com.votacaopauta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votacaopauta.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {

}