package com.victor.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.eventos.entities.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
	
}
