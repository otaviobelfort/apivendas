package com.spring.apivendas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.apivendas.entity.Produto;
import com.spring.apivendas.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/{id}")
	public Produto buscaProdutoPorId(@PathVariable Integer id) { 
		return produtoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Produto não encontrado!"));	
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto  buscaClientePorId(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarProdutoPorId(@PathVariable Integer id) {
		if(produtoRepository.findById(id).isPresent()) {
			produtoRepository.deleteById(id);
		}
	}
	
	@PutMapping("/atualizar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Produto produto ){{
                
    	produtoRepository.findById(id)
                           .map( produtoExistente -> {
                            produto.setId(produtoExistente.getId());
                            produtoRepository.save(produto);
                            return produtoExistente;})
                           
                           .orElseThrow(() -> new ResponseStatusException(
                        		           HttpStatus.NOT_FOUND,
                                          "Produto não foi encontrado"));
                            }
	}
	
	@GetMapping
	public List<Produto> find(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching()
												.withIgnoreCase()
												.withStringMatcher(
														ExampleMatcher.StringMatcher.CONTAINING);
		Example<Produto> example = Example.of(filtro,matcher);
		List<Produto> listaProdutos = produtoRepository.findAll(example);
		return listaProdutos;
		
	}
	
	

}
