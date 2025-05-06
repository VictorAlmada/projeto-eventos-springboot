package com.victor.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.eventos.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
}
