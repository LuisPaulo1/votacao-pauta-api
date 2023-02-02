package com.votacaopauta.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voto")
public class Voto {

	@EqualsAndHashCode.Include
	@Id
	private String cpfVotante;

	private String respostaDoVoto;

	@ManyToOne
	@JoinColumn(name = "sessao_votacao_id")
	private SessaoVotacao sessaoVotacao;
}
