package com.magdiel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magdiel.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
