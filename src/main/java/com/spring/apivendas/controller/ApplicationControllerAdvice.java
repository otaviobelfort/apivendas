package com.spring.apivendas.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.apivendas.entity.dto.ErrosApi;
import com.spring.apivendas.service.impl.exception.ApiVendasException;
import com.spring.apivendas.service.impl.exception.PedidoNaoEncontradoException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(ApiVendasException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrosApi handleApivendasException(ApiVendasException apiVendasException) {
		String messageErro = apiVendasException.getMessage();
		return new ErrosApi(messageErro);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrosApi handlePedidoNaoEncontradoNotFoundException(PedidoNaoEncontradoException ex) {
		return new ErrosApi(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrosApi handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String> erros = ex.getBindingResult().getAllErrors().stream().map(
				error -> error.getDefaultMessage())
								.collect(Collectors.toList());
		return new ErrosApi(erros);
	}

}
