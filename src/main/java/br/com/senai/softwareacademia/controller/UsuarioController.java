package br.com.senai.softwareacademia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.senai.softwareacademia.entity.Usuario;
import br.com.senai.softwareacademia.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private MapConverter converter;
	
	@Autowired
	@Qualifier("usuarioServiceProxy")
	private UsuarioService service;
	
	public ResponseEntity<?> inserir(
			@RequestBody
			Usuario usuario
			){
		Preconditions.checkArgument(usuario.isPersistido(), "O usuario deve possuir um nome para auteração");
		Usuario usuarioAtualizado = service.salvar(usuario);
		
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<?> buscarPor(
			@PathVariable("nome")
			String nome){
		Usuario usuarioEncontrado = service.buscarPor(nome);
		
		return ResponseEntity.ok(converter.toJsonMap(usuarioEncontrado));
	}
	
}
