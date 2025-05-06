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

import com.victor.eventos.dtos.CategoriaDTO;
import com.victor.eventos.services.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

	private final CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<CategoriaDTO> list = categoriaService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
		CategoriaDTO dto = categoriaService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO dto) {
		CategoriaDTO newDto = categoriaService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
		CategoriaDTO updatedDto = categoriaService.update(id, dto);
		return ResponseEntity.ok(updatedDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
