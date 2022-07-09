package com.spring.apivendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.apivendas.entity.Cliente;
import com.spring.apivendas.entity.ItemPedido;
import com.spring.apivendas.entity.Pedido;
import com.spring.apivendas.entity.Produto;
import com.spring.apivendas.entity.dto.DetalhesItemPedidoDTO;
import com.spring.apivendas.entity.dto.ItemPedidoDTO;
import com.spring.apivendas.entity.dto.PedidoDTO;
import com.spring.apivendas.repository.ClientesRepository;
import com.spring.apivendas.repository.ItensPedidoRepository;
import com.spring.apivendas.repository.PedidosRepository;
import com.spring.apivendas.repository.ProdutoRepository;
import com.spring.apivendas.service.PedidoService;
import com.spring.apivendas.service.impl.exception.ApiVendasException;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	private PedidosRepository pedidosRepository;
	private ProdutoRepository produtoRepository;
	private ClientesRepository clientesRepository;
	private ItensPedidoRepository itensPedidoRepository;
	
	private Pedido pedido;
	private Cliente cliente;
	private Produto produto;
	private ItemPedido itemPedido;
	private List<ItemPedido> listaItensPedido;
	
	public PedidoServiceImpl(PedidosRepository pedidosRepository,
							ProdutoRepository produtoRepository,
							ClientesRepository clientesRepository,
							ItensPedidoRepository itensPedidoRepository
							) {
				this.pedidosRepository = pedidosRepository;
				this.produtoRepository = produtoRepository;
				this.clientesRepository = clientesRepository;
				this.itensPedidoRepository = itensPedidoRepository;
	}
	
	@Override
	@Transactional
	public Pedido salvar(PedidoDTO pedidoDTO) {
		Cliente cliente = new Cliente();
		Pedido pedido = new Pedido();
		
		Integer idCliente = pedidoDTO.getCliente();
		cliente = clientesRepository.findById(idCliente).orElseThrow(
				()-> new ApiVendasException("Cliente não existe, para o ID:" + idCliente));
		
		pedido.setTotal(pedidoDTO.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		
		listaItensPedido = salvarItens(pedido, pedidoDTO.getItens());
		pedidosRepository.save(pedido);
		itensPedidoRepository.saveAll(listaItensPedido);
		pedido.setItens(listaItensPedido);
		return pedido;
		
	}
	
	private List<ItemPedido> salvarItens(Pedido pedidoSalvar, List<ItemPedidoDTO> itensDTO) {
		
		if(itensDTO.isEmpty()) {
			new ApiVendasException("Não há itens selecionados!");
		}

		return itensDTO
				.stream()
				.map( dto -> {
					Integer idProduto = dto.getProduto();
					produto = produtoRepository
							.findById(idProduto)
							.orElseThrow(
									() -> new ApiVendasException("Produto não existe, para o ID: " + idProduto)
									);
					
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedidoSalvar);
					itemPedido.setProduto(produto);
					return itemPedido;
				}).collect(Collectors.toList());
	}

	@Override
	public Optional<Pedido> detalhesPedido(Integer id) {
		DetalhesItemPedidoDTO pedidoItem;
		


		return pedidosRepository.findByFetchItens(id);
	}
	
	
}
