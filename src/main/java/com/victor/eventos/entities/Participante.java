package com.victor.eventos.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_participante")
@NoArgsConstructor
@Getter
@Setter
public class Participante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String nome;
	
	String email;
	
	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "id.participante")
	@JsonIgnore
	Set<Inscricao> inscricoes = new HashSet<>();
	
	public Participante(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
}
