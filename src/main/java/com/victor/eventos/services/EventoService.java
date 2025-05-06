package com.victor.eventos.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.eventos.dtos.EventoDTO;
import com.victor.eventos.entities.Categoria;
import com.victor.eventos.entities.Evento;
import com.victor.eventos.exceptions.DatabaseException;
import com.victor.eventos.exceptions.ResourceNotFoundException;
import com.victor.eventos.repositories.CategoriaRepository;
import com.victor.eventos.repositories.EventoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoService {
	
	private final EventoRepository eventoRepository;
	private final CategoriaRepository categoriaRepository;
	
	// LISTAR TODOS
	@Transactional(readOnly = true)
	public List<EventoDTO> findAll() {
		List<Evento> list = eventoRepository.findAll();
		return list.stream().map(EventoDTO::new).toList();
	}
	
	// BUSCAR POR ID
	@Transactional(readOnly = true)
	public EventoDTO findById(Long id) {
		Evento evento = eventoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + id));
		return new EventoDTO(evento);
	}
	
	// SALVAR
	@Transactional
	public EventoDTO insert(EventoDTO dto) {
		Evento entity = new Evento();
		copyDtoToEntity(dto, entity);
		entity = eventoRepository.save(entity);
		return new EventoDTO(entity);
	}
	
	// ATUALIZAR
	@Transactional
	public EventoDTO update(Long id, EventoDTO dto) {
		try {
			Evento entity = eventoRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			return new EventoDTO(eventoRepository.save(entity));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID não encontrado: " + id);
		}
	}
	
	// DELETAR
	public void delete(Long id) {
		try {
			eventoRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID não encontrado: " + id);
		} catch (Exception e) {
			throw new DatabaseException("Erro ao deletar recurso");
		}
	}
	
	// MÉTODO AUXILIAR PARA TRANSFORMAR O DTO EM ENTIDADE
	private void copyDtoToEntity(EventoDTO dto, Evento entity) {
		entity.setNome(dto.getNome());
		entity.setLocal(dto.getLocal());
		entity.setData(dto.getData());
		
		Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
		entity.setCategoria(categoria);
	}
	
	
}
