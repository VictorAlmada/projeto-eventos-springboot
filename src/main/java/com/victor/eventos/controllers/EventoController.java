package com.victor.eventos.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.eventos.dtos.EventoDTO;
import com.victor.eventos.services.EventoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {
	
	private final EventoService eventoService;
	
	@GetMapping
	public ResponseEntity<List<EventoDTO>> findAll() {
		return ResponseEntity.ok(eventoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EventoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(eventoService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<EventoDTO> insert(@RequestBody EventoDTO dto) {
		EventoDTO created = eventoService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
		return ResponseEntity.created(uri).body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EventoDTO> update(@PathVariable Long id, @RequestBody EventoDTO dto) {
		return ResponseEntity.ok(eventoService.update(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		eventoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
