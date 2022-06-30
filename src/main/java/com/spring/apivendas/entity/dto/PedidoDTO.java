package com.spring.apivendas.entity.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class PedidoDTO {
	
	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> itens;
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<ItemPedidoDTO> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedidoDTO> itens) {
		this.itens = itens;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cliente, itens, total);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDTO other = (PedidoDTO) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(itens, other.itens)
				&& Objects.equals(total, other.total);
	}
	
	
	
	
	
}
