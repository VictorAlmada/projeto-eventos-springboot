package com.victor.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.eventos.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	
}
