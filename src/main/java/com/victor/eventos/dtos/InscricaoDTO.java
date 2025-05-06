package com.victor.eventos.dtos;

import java.time.Instant;

import com.victor.eventos.entities.Inscricao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InscricaoDTO {

	private Long participanteId;
	private Long eventoId;
	private Instant dataInscricao;

	public InscricaoDTO(Inscricao entity) {
		this.participanteId = entity.getParticipante().getId();
		this.eventoId = entity.getEvento().getId();
		this.dataInscricao = entity.getDataInscricao();
	}

}
