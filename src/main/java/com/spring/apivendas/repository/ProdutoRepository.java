package com.spring.apivendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.apivendas.entity.Produto;

public interface ProdutosRepository extends JpaRepository<Produto,Integer> {
}
