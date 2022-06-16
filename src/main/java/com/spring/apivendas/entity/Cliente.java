package com.spring.apivendas.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table( name = "cliente" )
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 50)
    private String nome;
    
    @Column(name = "cpf", length = 11)
    private String cpf;
    
    @JsonIgnore
    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   
    public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}