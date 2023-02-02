package com.votacaopauta.controller.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaInputDto {

	@ApiModelProperty(example = "Sessão Extraordinária de encerramento do ano forense", required = true)
	@NotBlank
	private String nome;
}
