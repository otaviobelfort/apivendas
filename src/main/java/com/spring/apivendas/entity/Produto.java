package com.spring.apivendas.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @NotEmpty(message = "Campo 'produto' é obrigatório!")
    @Column(name = "descricao")
    private String descricao;
    
    @NotNull(message = "Campo 'preço' é obrigatório!")
    @Column(name = "preco_unitario")
    private BigDecimal preco;

 
}