package com.victor.eventos.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victor.eventos.entities.Categoria;
import com.victor.eventos.entities.Evento;
import com.victor.eventos.entities.Inscricao;
import com.victor.eventos.entities.Participante;
import com.victor.eventos.repositories.CategoriaRepository;
import com.victor.eventos.repositories.EventoRepository;
import com.victor.eventos.repositories.InscricaoRepository;
import com.victor.eventos.repositories.ParticipanteRepository;

@Configuration
public class TestConfig implements CommandLineRunner {
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ParticipanteRepository participanteRepository;
	@Autowired
	private InscricaoRepository inscricaoRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Tecnologia");
		Categoria c2 = new Categoria(null, "Educação");
		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		
		Evento e1 = new Evento(null, "Spring Boot Workshop", "Online", Instant.parse("2025-02-10T10:00:00Z"), c1);
		Evento e2 = new Evento(null, "Feira de Ciências", "Escola Municipal", Instant.parse("2025-06-05T14:00:00Z"), c1);
		eventoRepository.saveAll(Arrays.asList(e1, e2));
		
		Participante p1 = new Participante(null, "Alice", "alice@email.com");
		Participante p2 = new Participante(null, "Bob", "bob@email.com");
		participanteRepository.saveAll(Arrays.asList(p1, p2));
		
		Inscricao i1 = new Inscricao(p1, e1, Instant.now());
		Inscricao i2 = new Inscricao(p2, e1, Instant.now());
		Inscricao i3 = new Inscricao(p1, e2, Instant.now());
		inscricaoRepository.saveAll(Arrays.asList(i1, i2, i3));		
	}

}
