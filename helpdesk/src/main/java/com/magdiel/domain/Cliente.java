package com.magdiel.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.magdiel.domain.DTOs.ClienteDTO;
import com.magdiel.domain.enums.Perfil;


@Entity
public class Cliente extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "cliente")
	public List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
		addPerfis(Perfil.CLIENTE);
	}

	public Cliente(Integer id,
				   String name, 
				   String cpf, 
				   String email, 
				   String senha) {
				   super(id, name, cpf, email, senha);
				   addPerfis(Perfil.CLIENTE);
	}
	public Cliente(ClienteDTO obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
		addPerfis(Perfil.CLIENTE);
	}
	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
}
