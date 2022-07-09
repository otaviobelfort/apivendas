package com.spring.apivendas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.apivendas.entity.dto.ErrosApi;
import com.spring.apivendas.service.impl.exception.ApiVendasException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(ApiVendasException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrosApi handleApivendasException(ApiVendasException apiVendasException) {
		String messageErro = apiVendasException.getMessage();
		return new ErrosApi(messageErro);
	}

}
