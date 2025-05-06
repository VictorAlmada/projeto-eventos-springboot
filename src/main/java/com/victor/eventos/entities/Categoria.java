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
@Table(name = "tb_categoria")
@NoArgsConstructor
@Getter
@Setter
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String nome;
	
	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "categoria")
	@JsonIgnore
	Set<Evento> eventos = new HashSet<>();
	
	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	
	
}
