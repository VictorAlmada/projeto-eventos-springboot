package com.victor.eventos.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.eventos.dtos.CategoriaDTO;
import com.victor.eventos.entities.Categoria;
import com.victor.eventos.exceptions.DatabaseException;
import com.victor.eventos.exceptions.ResourceNotFoundException;
import com.victor.eventos.repositories.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll() {
		return categoriaRepository.findAll().stream().map(CategoriaDTO::new).toList();
	}
	
	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
		return new CategoriaDTO(categoria);
	}
	
	@Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = new Categoria();
		entity.setNome(dto.getNome());
		entity = categoriaRepository.save(entity);
		return new CategoriaDTO(entity);
	}
	
	@Transactional
	public CategoriaDTO update(Long id, CategoriaDTO dto) {
		try {
			Categoria entity = categoriaRepository.getReferenceById(id);
			entity.setNome(dto.getNome());
			entity = categoriaRepository.save(entity);
			return new CategoriaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Categoria não encontrada");
		}
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			categoriaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Categoria não encontrada");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}
	
}
