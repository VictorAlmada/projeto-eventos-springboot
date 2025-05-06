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

import com.victor.eventos.dtos.ParticipanteDTO;
import com.victor.eventos.services.ParticipanteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/participantes")
@RequiredArgsConstructor
public class ParticipanteController {
	
	private final ParticipanteService participanteService;
	
	@GetMapping
	public ResponseEntity<List<ParticipanteDTO>> findAll() {
		List<ParticipanteDTO> list = participanteService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ParticipanteDTO> findById(@PathVariable Long id) {
		ParticipanteDTO dto = participanteService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ParticipanteDTO> insert(@RequestBody ParticipanteDTO dto) {
		ParticipanteDTO newDto = participanteService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ParticipanteDTO> update(@PathVariable Long id, @RequestBody ParticipanteDTO dto) {
		dto = participanteService.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		participanteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
