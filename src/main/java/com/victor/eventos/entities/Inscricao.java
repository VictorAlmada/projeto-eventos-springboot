package com.victor.eventos.entities;

import java.time.Instant;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_inscricao")
@NoArgsConstructor
@EqualsAndHashCode
public class Inscricao {

	@EmbeddedId
	private InscricaoPK id = new InscricaoPK();

	private Instant dataInscricao;

	public Inscricao(Participante participante, Evento evento, Instant dataInscricao) {
		this.id.setParticipante(participante);
		this.id.setEvento(evento);
		this.dataInscricao = dataInscricao;
	}

	public Participante getParticipante() {
		return id.getParticipante();
	}

	public void setParticipante(Participante participante) {
		id.setParticipante(participante);
	}

	public Evento getEvento() {
		return id.getEvento();
	}

	public void setEvento(Evento evento) {
		id.setEvento(evento);
	}

	public Instant getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Instant dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

}
