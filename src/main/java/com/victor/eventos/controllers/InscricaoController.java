package com.victor.eventos.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.eventos.dtos.InscricaoDTO;
import com.victor.eventos.services.InscricaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {

	private final InscricaoService inscricaoService;
	
	@GetMapping
	public ResponseEntity<List<InscricaoDTO>> findAll() { 
		List<InscricaoDTO> list = inscricaoService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<InscricaoDTO> insert(@RequestBody InscricaoDTO dto) {
		InscricaoDTO newDto = inscricaoService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.query("participanteId={pid}&eventoId={eid}")
				.buildAndExpand(newDto.getParticipanteId(), newDto.getEventoId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@DeleteMapping("/participante/{participanteId}/evento/{eventoId}")
	public ResponseEntity<Void> delete(@PathVariable Long participanteId, @PathVariable Long eventoId) {
		inscricaoService.delete(participanteId, eventoId);
		return ResponseEntity.noContent().build();
	}
	
	
}
