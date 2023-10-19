package br.com.senai.softwareacademia.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.senai.softwareacademia.dto.SolicitacaoDeToken;
import br.com.senai.softwareacademia.security.GerenciadorDeTokenJwt;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private GerenciadorDeTokenJwt gerenciadorDeToken;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping
	public ResponseEntity<?> logar(
			@RequestBody 
			SolicitacaoDeToken solicitacao){
		
		Authentication tokenAutenticado = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(solicitacao.getLogin(), solicitacao.getSenha()));
		Preconditions.checkArgument(tokenAutenticado.isAuthenticated(), "Login e/ou senha invalidos");
		String tokenGerado = gerenciadorDeToken.gerarTokenPor(solicitacao.getLogin());
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("token", tokenGerado);
		return ResponseEntity.ok(response);
	}
	
}