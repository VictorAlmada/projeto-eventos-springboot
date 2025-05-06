package com.victor.eventos.dtos;

import com.victor.eventos.entities.Categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaDTO {
	private Long id;
	private String nome;
	
	public CategoriaDTO(Categoria entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
	}
}
