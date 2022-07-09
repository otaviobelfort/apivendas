package com.spring.apivendas.service;

import java.util.List;
import java.util.Optional;

import com.spring.apivendas.entity.ItemPedido;
import com.spring.apivendas.entity.Pedido;
import com.spring.apivendas.entity.dto.ItemPedidoDTO;
import com.spring.apivendas.entity.dto.PedidoDTO;

public interface PedidoService {
	
	public Pedido salvar(PedidoDTO pedidoDTO);
	Optional<Pedido> detalhesPedido(Integer id);
//	List<ItemPedido> salvarItens(Pedido pedidoSalvar, List<ItemPedidoDTO> itensDTO);

}
