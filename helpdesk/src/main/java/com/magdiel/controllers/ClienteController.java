package com.magdiel.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.magdiel.domain.Cliente;
import com.magdiel.domain.DTOs.ClienteDTO;
import com.magdiel.services.ClienteService;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	
	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> tecnico = service.findAll();
		List<ClienteDTO> tecnicoDTO = tecnico.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(tecnicoDTO);		
	}
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO tecnicoDTO){
		Cliente tecnio = service.create(tecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnio.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO) {
		Cliente obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> Delete(@PathVariable Integer id){
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}
}
