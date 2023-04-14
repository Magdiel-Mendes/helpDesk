package com.magdiel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magdiel.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
