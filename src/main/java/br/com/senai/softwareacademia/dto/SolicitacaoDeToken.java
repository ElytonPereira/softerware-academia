package br.com.senai.softwareacademia.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SolicitacaoDeToken {

	@NotBlank(message = "O login é obrigatório")
	private String login;
	
	@NotBlank(message = "A senha é obrigatória")
	private String senha;
	
}