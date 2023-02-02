package com.votacaopauta.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResultDto {

	@ApiModelProperty(example = "ABLE_TO_VOTE")
	private String status;
}
