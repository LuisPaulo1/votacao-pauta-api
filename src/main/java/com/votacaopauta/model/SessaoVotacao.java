package com.votacaopauta.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sessao_votacao")
public class SessaoVotacao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDateTime dataHoraAbertura;

	private LocalDateTime dataHoraFechamento;

	@OneToOne
	@JoinColumn(name = "pauta_id")
	private Pauta pauta;

	@OneToMany(mappedBy = "sessaoVotacao", fetch = FetchType.EAGER)
	private Set<Voto> votos = new HashSet<>();

	public Map<String, Long> obterQuantidadeDeVotos() {
		long quantidadeDeVotosSim = this.votos.stream().filter(voto -> voto.getRespostaDoVoto().equals("Sim")).count();
		long quantidadeDeVotosNao = this.votos.stream().filter(voto -> voto.getRespostaDoVoto().equals("Não")).count();
		Map<String, Long> resultado = new HashMap<>();
		resultado.put("Sim", quantidadeDeVotosSim);
		resultado.put("Não", quantidadeDeVotosNao);
		return  resultado;
	}
}
