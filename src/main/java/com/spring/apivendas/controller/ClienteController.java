package com.spring.apivendas.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.apivendas.entity.Cliente;
import com.spring.apivendas.repository.ClientesRepository;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClientesRepository clientesRepository;

	@GetMapping("/{id}")
	public Cliente buscaClientePorId(@PathVariable Integer id) { 
		return clientesRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Cliente não encontrado!"));	
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente  buscaClientePorId(@RequestBody @Valid Cliente cliente) {
		return clientesRepository.save(cliente);
	}
	
	@DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Integer id) {
		if(clientesRepository.findById(id).isPresent()) {
			clientesRepository.deleteById(id);
		}
	}
	
	@PutMapping("/atualizar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody Cliente cliente ){{
                
    	clientesRepository.findById(id)
                           .map( clienteExistente -> {
                            cliente.setId(clienteExistente.getId());
                            clientesRepository.save(cliente);
                            return clienteExistente;})
                           
                           .orElseThrow(() -> new ResponseStatusException(
                        		           HttpStatus.NOT_FOUND,
                                          "Cliente não foi encontrado"));
                            }
	}
	
	@GetMapping
	public List<Cliente> find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching()
												.withIgnoreCase()
												.withStringMatcher(
														ExampleMatcher.StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(filtro,matcher);
		List<Cliente> listaClientes = clientesRepository.findAll(example);
		return listaClientes;
		
	}
	
}
