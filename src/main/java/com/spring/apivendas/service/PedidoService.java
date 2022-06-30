package com.spring.apivendas.service;

import java.util.List;

import com.spring.apivendas.entity.ItemPedido;
import com.spring.apivendas.entity.Pedido;
import com.spring.apivendas.entity.dto.ItemPedidoDTO;
import com.spring.apivendas.entity.dto.PedidoDTO;

public interface PedidoService {
	
	public Pedido salvar(PedidoDTO pedidoDTO);
//	List<ItemPedido> salvarItens(Pedido pedidoSalvar, List<ItemPedidoDTO> itensDTO);

}
