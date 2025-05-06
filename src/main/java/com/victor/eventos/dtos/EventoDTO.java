package com.victor.eventos.dtos;

import java.time.Instant;

import com.victor.eventos.entities.Evento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventoDTO {
	
	private Long id;
	private String nome;
	private String local;
	private Instant data;
	private Long categoriaId;
	
	public EventoDTO(Evento entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.local = entity.getLocal();
		this.data = entity.getData();
		this.categoriaId = entity.getCategoria().getId();
	}
	
}
