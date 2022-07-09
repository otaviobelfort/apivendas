package com.spring.apivendas.service.impl.exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class PedidoNaoEncontradoException extends RuntimeException{
	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado!");
	}
	

}
