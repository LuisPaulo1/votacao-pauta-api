package com.votacaopauta.controller.dto;

import javax.validation.constraints.NotBlank;

import com.votacaopauta.validation.RespostaVoto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
public class VotoInputDto {

	@ApiModelProperty(example = "550.122.806-87", required = true)
	@CPF(message = "CPF inválido.")
	@NotBlank
	private String cpfVotante;

	@ApiModelProperty(example = "Sim", required = true)
	@NotBlank
	@RespostaVoto
	private String respostaDoVoto;
}
