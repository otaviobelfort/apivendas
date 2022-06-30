package com.spring.apivendas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.apivendas.entity.Pedido;
import com.spring.apivendas.entity.dto.PedidoDTO;
import com.spring.apivendas.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	private PedidoService pedidoService;
	public Pedido pedido;
	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer salvarPedido(@RequestBody PedidoDTO pedidoDTO) {
		pedido = pedidoService.salvar(pedidoDTO);
		return pedido.getId();
	}
}
