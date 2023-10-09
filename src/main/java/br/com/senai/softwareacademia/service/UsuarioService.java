package br.com.senai.softwareacademia.service;

import org.springframework.validation.annotation.Validated;

import br.com.senai.softwareacademia.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface UsuarioService {

	public Usuario salvar(
			@NotNull(message = "O usuario é obrigatório")
			Usuario usuario
			); 
	
	public Usuario buscarPor(
			@NotBlank(message = "O nome do usuario é obrigatório")			
			String nome);
	
}
