package com.votacaopauta.controller.openapi;

import com.votacaopauta.controller.dto.UserResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Api(tags = "Users")
public interface UserInfoControllerOpenApi {

	@ApiOperation("Valida CPF")
	@ApiResponses({
		@ApiResponse(code = 200, message = "ABLE_TO_VOTE."),
		@ApiResponse(code = 404, message = "UNABLE_TO_VOTE.")
	})
	ResponseEntity<UserResultDto> validar(@PathVariable String cpf);
}
