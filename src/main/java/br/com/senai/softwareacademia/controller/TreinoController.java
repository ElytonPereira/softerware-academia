package br.com.senai.softwareacademia.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.senai.softwareacademia.entity.Exercicio;
import br.com.senai.softwareacademia.entity.Treino;
import br.com.senai.softwareacademia.entity.enums.ObjetivoDoTreino;
import br.com.senai.softwareacademia.service.TreinoService;

public class TreinoController {

	
	@Autowired
	private MapConverter converter;

	@Autowired
	@Qualifier("TreinoService")
	private TreinoService service;

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Treino treino) {

		Treino treinoSalvo = service.inserir(treino);

		return ResponseEntity.created(URI.create("/treino/id/" + treinoSalvo.getId())).build();

	}
	
	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Treino treino) {

		Treino treinoAtualizado = service.alterar(treino);

		return ResponseEntity.ok(converter.toJsonMap(treinoAtualizado));
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		Treino treinoEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(treinoEncontrado));
	}

	@GetMapping
	public ResponseEntity<?> listarPor(@RequestParam("objetivo") ObjetivoDoTreino objetivo) {

		Page<Treino> treino = service.listarPor(objetivo);

		return ResponseEntity.ok(converter.toJsonList(treino));

	}

	@GetMapping
	public ResponseEntity<?> listarPor(@RequestParam("treino") Treino treino,
			@RequestParam("pagina") Optional<Integer> pagina) {
		Pageable paginacao = null;

		if (pagina.isPresent()) {
			paginacao = PageRequest.of(pagina.get(), 15);
		} else {
			paginacao = PageRequest.of(0, 15);

		}

		Page<Exercicio> exercicio = service.listarPor(treino, paginacao);

		return ResponseEntity.ok(converter.toJsonList(exercicio));

	}
	
	
	
	
	
	
	
	
}
