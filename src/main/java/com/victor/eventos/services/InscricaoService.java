package com.victor.eventos.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.eventos.dtos.InscricaoDTO;
import com.victor.eventos.entities.Evento;
import com.victor.eventos.entities.Inscricao;
import com.victor.eventos.entities.InscricaoPK;
import com.victor.eventos.entities.Participante;
import com.victor.eventos.exceptions.DatabaseException;
import com.victor.eventos.exceptions.ResourceNotFoundException;
import com.victor.eventos.repositories.EventoRepository;
import com.victor.eventos.repositories.InscricaoRepository;
import com.victor.eventos.repositories.ParticipanteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscricaoService {

	private final InscricaoRepository inscricaoRepository;
	private final ParticipanteRepository participanteRepository;
	private final EventoRepository eventoRepository;
	
	@Transactional(readOnly = true)
	public List<InscricaoDTO> findAll() {
		return inscricaoRepository.findAll().stream().map(InscricaoDTO::new).toList();
	}
	
	@Transactional
	public InscricaoDTO insert(InscricaoDTO dto) {
		Participante participante = participanteRepository.findById(dto.getParticipanteId())
				.orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado"));
		
		Evento evento = eventoRepository.findById(dto.getEventoId())
				.orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
		
		Inscricao entity = new Inscricao(participante, evento, dto.getDataInscricao());
		entity = inscricaoRepository.save(entity);
		return new InscricaoDTO(entity);
	}
	
	@Transactional
	public void delete(Long participanteId, Long EventoId) {
		try {
			Participante p = participanteRepository.getReferenceById(participanteId);
			Evento e = eventoRepository.getReferenceById(EventoId);
			InscricaoPK id = new InscricaoPK(p, e);
			inscricaoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Inscrição não encontrada");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade referencial");
		}
	}
	
	
	
}
