package com.victor.eventos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.victor.eventos.dtos.ParticipanteDTO;
import com.victor.eventos.entities.Participante;
import com.victor.eventos.exceptions.DatabaseException;
import com.victor.eventos.exceptions.ResourceNotFoundException;
import com.victor.eventos.repositories.ParticipanteRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteService {

	private final ParticipanteRepository participanteRepository;
	
	public List<ParticipanteDTO> findAll() {
		return participanteRepository.findAll().stream().map(ParticipanteDTO::new).toList();
	}
	
	public ParticipanteDTO findById(Long id) {
		Participante participante = participanteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado com ID: " + id));
		return new ParticipanteDTO(participante);
	}
	
	public ParticipanteDTO insert(ParticipanteDTO dto) {
		Participante participante = new Participante();
		participante.setNome(dto.getNome());
		participante.setEmail(dto.getEmail());
		participante = participanteRepository.save(participante);
		return new ParticipanteDTO(participante);
	}
	
	public ParticipanteDTO update(Long id, ParticipanteDTO dto) {
		try {
			Participante participante = participanteRepository.getReferenceById(id);
			participante.setNome(dto.getNome());
			participante.setEmail(dto.getEmail());
			participante = participanteRepository.save(participante);
			return new ParticipanteDTO(participante);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID não encontrado: " + id);
		}
	}
	
	public void delete(Long id) {
		if (!participanteRepository.existsById(id)) {
			throw new ResourceNotFoundException("ID não encontrado: " + id);
		}
		try {
			
		} catch (Exception e) {
			throw new DatabaseException("Violação de integridade referencial");
		}
	}
}
