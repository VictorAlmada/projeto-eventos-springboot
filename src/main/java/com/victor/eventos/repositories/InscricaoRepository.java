package com.victor.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.eventos.entities.Inscricao;
import com.victor.eventos.entities.InscricaoPK;

public interface InscricaoRepository extends JpaRepository<Inscricao, InscricaoPK> {

}
