package com.votacaopauta.controller.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaResultadoDto {

	@ApiModelProperty(example = "1")
	private Integer id;

	@ApiModelProperty(example = "Sessão Extraordinária de encerramento do ano forense")
	private String nome;

	@ApiModelProperty(example = "Sim: 5, Não: 3")
	private Map<String, Long> quantidadeDeVotos;

	@ApiModelProperty(example = "Pauta aprovada")
	private String resultadoFinal;

}
