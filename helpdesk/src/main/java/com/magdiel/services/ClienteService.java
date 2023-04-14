package com.magdiel.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magdiel.Repository.PessoaRepository;
import com.magdiel.Repository.ClienteRepository;
import com.magdiel.domain.Pessoa;
import com.magdiel.domain.Cliente;
import com.magdiel.domain.DTOs.ClienteDTO;
import com.magdiel.services.exceptions.DataIntegrityViolationException;
import com.magdiel.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired 
	private PessoaRepository pessoaRepo;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado " + id));
	}
	

	// metodo para buscar todos as pessoas idependente do perfil
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	// Metodo para criar um tecnico
	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);

		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}

		repository.deleteById(id);
	}


	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> tecnico = pessoaRepo.findByCpf(objDTO.getCpf());
		if (tecnico.isPresent() && tecnico.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		tecnico = pessoaRepo.findByEmail(objDTO.getEmail());
		if (tecnico.isPresent() && tecnico.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}