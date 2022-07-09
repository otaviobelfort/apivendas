package com.spring.apivendas.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "cliente" )
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 50)
    @NotEmpty(message="Campo 'nome' é obrigatório!")
    private String nome;
    
    @NotEmpty(message="Campo 'CPF' é obrigatório!")
    @CPF(message = "Informe um CPF válido." )
    @Column(name = "cpf", length = 11)
    private String cpf;
    
    @JsonIgnore
    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private List<Pedido> pedidos;
}