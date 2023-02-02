package com.votacaopauta.client;

import com.votacaopauta.controller.dto.UserResultDto;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Component
public class UserInfoClient {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UrlProperties urlProperties;

	@Retry(name = "retryInstance", fallbackMethod = "retryfallback")
	public UserResultDto validar(String cpf) {
		ResponseEntity<UserResultDto> resultado = null;
		String url = urlProperties.getUrl() + "/users/"+cpf;
		log.info("Iniciando request para URL {} ", url);
		try {
			ParameterizedTypeReference<UserResultDto> responseType = new ParameterizedTypeReference<>() {};
			resultado = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
			log.info("Request para a URL {} realizada com sucesso.", url);
		} catch (HttpStatusCodeException e) {
			log.error("Error {}", e.getMessage());
			return null;
		}
		return resultado.getBody();
	}

	public UserResultDto retryfallback(String cpf, Throwable t) {
		log.error("Método retryfallback do retry chamado, causa - {}", t.toString());
		throw new ResourceAccessException(t.getMessage());
	}

}
