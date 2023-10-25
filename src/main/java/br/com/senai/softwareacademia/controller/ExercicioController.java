package br.com.senai.softwareacademia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.softwareacademia.entity.Exercicio;
import br.com.senai.softwareacademia.entity.Treino;
import br.com.senai.softwareacademia.entity.enums.GrupoDoExercicio;
import br.com.senai.softwareacademia.entity.enums.ObjetivoDoTreino;
import br.com.senai.softwareacademia.service.ExercicioService;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

	@Autowired
	private MapConverter converter;

	@Autowired
	@Qualifier("exercicioServiceImpl")
	private ExercicioService service;

	


	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		Exercicio exercicioEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(exercicioEncontrado));
		
	}

	@GetMapping("/grupo/{grupo}/pagina/{pagina}")
	public ResponseEntity<?> listarPorGrupo(@PathVariable("grupo") GrupoDoExercicio grupo,@PathVariable("pagina") Optional<Integer> pagina) {
		Pageable paginacao = null;
		if (pagina.isPresent()) {
			paginacao = PageRequest.of(pagina.get(), 15);
		} else {
			paginacao = PageRequest.of(0, 15);

		}
		Page<Exercicio> exercicio = service.listarPor(grupo, paginacao);

		return ResponseEntity.ok(converter.toJsonList(exercicio));		

	}

	@GetMapping
	public ResponseEntity<?> listarPorTreino(@RequestParam("treino") Treino treino,
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
