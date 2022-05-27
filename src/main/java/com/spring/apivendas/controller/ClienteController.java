package com.spring.apivendas.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ResponseEntity buscaTodosClientes() { 
			return ResponseEntity.ok(clientesRepository.findAll());
		}
	
	@PostMapping("/salvar")
	@ResponseBody
	public ResponseEntity buscaClientePorId(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(clientesRepository.save(cliente));
	}
	
	@GetMapping("/deletar/{id}")
	@ResponseBody
	public void deletarPorId(@PathVariable Integer id) {
		clientesRepository.deleteById(id);
//		return ResponseEntity.ok();
	}
	

}
