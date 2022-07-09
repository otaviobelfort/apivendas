package com.spring.apivendas.service;

import java.util.List;
import java.util.Optional;

import com.spring.apivendas.entity.ItemPedido;
import com.spring.apivendas.entity.Pedido;
import com.spring.apivendas.entity.dto.ItemPedidoDTO;
import com.spring.apivendas.entity.dto.PedidoDTO;
import com.spring.apivendas.enums.StatusPedido;

public interface PedidoService {
	
	public Pedido salvar(PedidoDTO pedidoDTO);
	Optional<Pedido> detalhesPedido(Integer id);
	void atualizarStatus(Integer id, StatusPedido statusPedido);
//	List<ItemPedido> salva;rItens(Pedido pedidoSalvar, List<ItemPedidoDTO> itensDTO);

}
