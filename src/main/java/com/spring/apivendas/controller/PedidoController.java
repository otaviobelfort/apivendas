package com.spring.apivendas.controller;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.util.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.apivendas.entity.ItemPedido;
import com.spring.apivendas.entity.Pedido;
import com.spring.apivendas.entity.dto.DetalhesItemPedidoDTO;
import com.spring.apivendas.entity.dto.DetalhesPedidoDTO;
import com.spring.apivendas.entity.dto.PedidoDTO;
import com.spring.apivendas.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	private PedidoService pedidoService;

	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer salvarPedido(@RequestBody PedidoDTO pedidoDTO) {
		 Pedido pedido = pedidoService.salvar(pedidoDTO);
		return pedido.getId();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public DetalhesPedidoDTO visualizarPedidoId(@PathVariable Integer id) {
		return pedidoService
				.detalhesPedido(id)
				.map( p -> converterPedido(p) )
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado para o ID: "+id));
	}
	
	private DetalhesPedidoDTO converterPedido(Pedido pedido) {
		return DetalhesPedidoDTO
								.builder()
								.id(pedido.getId())
								.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
								.cpf(pedido.getCliente().getCpf())
								.nome(pedido.getCliente().getNome())
								.total(pedido.getTotal())
								.itensPedido(conveterItemPedido(pedido.getItens()))
								.build();
								
								
	}
	
	private List<DetalhesItemPedidoDTO> conveterItemPedido(List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		return itens.stream().map(
				item -> DetalhesItemPedidoDTO
											.builder()
											.descricaoProduto(item.getProduto().getDescricao())
											.precoUnitario(item.getProduto().getPreco())
											.quantidade(item.getQuantidade())
											.build()
						).collect(Collectors.toList());
	}
	
}
