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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.softwareacademia.entity.Exercicio;
import br.com.senai.softwareacademia.entity.Treino;
import br.com.senai.softwareacademia.entity.enums.ObjetivoDoTreino;
import br.com.senai.softwareacademia.service.ExercicioService;

@RestController
@RequestMapping("/exercicio")
public class ExercicioController {

	@Autowired
	private MapConverter converter;

//	@Autowired
//	@Qualifier("exercicioService")
//	private ExercicioService service;

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Exercicio exercicio) {

		//Exercicio exercicioSalvo = service.inserir(exercicio);

		//return ResponseEntity.created(URI.create("/exercicio/id/" + exercicioSalvo.getId())).build();
		return null;

	}

	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Exercicio exercicio) {

		//Exercicio exercicioAtualizado = service.alterar(exercicio);

		//return ResponseEntity.ok(converter.toJsonMap(exercicioAtualizado));
		return null;
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		/*Exercicio exercicioEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(exercicioEncontrado));*/
		return null;
	}

	/*@GetMapping
	public ResponseEntity<?> listarPor(@RequestParam("objetivo") ObjetivoDoTreino objetivo) {

		Page<Exercicio> exercicio = service.listarPor(objetivo);

		return ResponseEntity.ok(converter.toJsonList(exercicio));		

	}*/

	@GetMapping
	public ResponseEntity<?> listarPor(@RequestParam("treino") Treino treino,
			@RequestParam("pagina") Optional<Integer> pagina) {
		/*Pageable paginacao = null;

		if (pagina.isPresent()) {
			paginacao = PageRequest.of(pagina.get(), 15);
		} else {
			paginacao = PageRequest.of(0, 15);

		}

		Page<Exercicio> exercicio = service.listarPor(treino, paginacao);

		return ResponseEntity.ok(converter.toJsonList(exercicio));*/
		return null;

	}

}
