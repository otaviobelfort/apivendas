package com.spring.apivendas.entity.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.spring.apivendas.validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {
	@NotNull(message = "Informe o código do cliente!")
	private Integer cliente;
	
	@NotNull(message = "Informe o total do pedido!")
	private BigDecimal total;
	
	@NotEmptyList(message = "O pedido não pode ser realizado sem itens!")
	private List<ItemPedidoDTO> itens;
		
}
