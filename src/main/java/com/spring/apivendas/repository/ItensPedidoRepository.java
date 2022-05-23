package com.spring.apivendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.apivendas.entity.ItemPedido;

public interface ItensPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
