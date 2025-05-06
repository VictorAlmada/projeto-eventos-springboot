package com.victor.eventos.dtos;

import com.victor.eventos.entities.Participante;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipanteDTO {
	
	private Long id;
	private String nome;
	private String email;
	
	public ParticipanteDTO(Participante entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
	}
	
}
