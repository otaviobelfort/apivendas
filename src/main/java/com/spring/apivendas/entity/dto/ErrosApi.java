package com.spring.apivendas.entity.dto;

import java.util.Arrays;
import java.util.List;

public class ErrosApi {
	
	List<String> erros;

	public ErrosApi(String messageErro) {
		this.erros = Arrays.asList(messageErro);
	}
	
	public ErrosApi(List<String> erros) {
		this.erros = erros;
	}
		
		
	
	public List<String> getErros() {
		return erros;
	}


}
