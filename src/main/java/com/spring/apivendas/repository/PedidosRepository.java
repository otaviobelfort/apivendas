package com.spring.apivendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.apivendas.entity.Cliente;
import com.spring.apivendas.entity.Pedido;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
