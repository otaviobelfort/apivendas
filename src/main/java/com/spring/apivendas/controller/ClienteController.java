package com.spring.apivendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.apivendas.entity.Cliente;
import com.spring.apivendas.repository.ClientesRepository;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClientesRepository clientesRepository;

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity buscaClientePorId(@PathVariable Integer id) { //@PathVariable("id") @RequestBody Integer id)
		Optional<Cliente> clientes = clientesRepository.findById(id);
		if(clientes.isPresent()) {
			return ResponseEntity.ok(clientes.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	@ResponseBody
	public java.util.List<Cliente> listaClientes() { //@PathVariable("id") @RequestBody Integer id)
		return clientesRepository.findAll();
	}
	
//	@PostMapping("/salvar")
//	@ResponseBody
//	public ResponseEntity salvarCliente(@RequestBody Cliente cliente) { //@PathVariable("id") @RequestBody Integer id)
//		if(cliente!=null) {
//			return ResponseEntity.ok(clientesRepository.save(cliente));
//		}
//		
//		return ResponseEntity.notFound().build();
//	}
	
	public ResponseEntity buscaTodosClientes() { 
			return ResponseEntity.ok(clientesRepository.findAll());
		}
	
	@PostMapping("/salvar")
	@ResponseBody
	public ResponseEntity  buscaClientePorId(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(clientesRepository.save(cliente));
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseBody
	public ResponseEntity deletarPorId(@PathVariable Integer id) {
		if(clientesRepository.findById(id).isPresent()) {
			clientesRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/atualizar/{id}")
    @ResponseBody
    public ResponseEntity update( @PathVariable Integer id,
                                  @RequestBody Cliente cliente ){
        return clientesRepository
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.notFound().build() );
    }
	
	@GetMapping
	@ResponseBody
	public ResponseEntity find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching()
												.withIgnoreCase()
												.withStringMatcher(
														ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro,matcher);
		List<Cliente> listaClientes = clientesRepository.findAll(example);
		return ResponseEntity.ok(listaClientes);
		
	}
	
}
