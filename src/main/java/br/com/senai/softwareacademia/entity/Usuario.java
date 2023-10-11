package br.com.senai.softwareacademia.entity;

import br.com.senai.softwareacademia.entity.enums.Papel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuario")
@Entity(name = "Usuario")
public class Usuario {
	
	@Id
	@Size(min = 4, max = 50, message = "O login deve possuir entre 4 e 50 caracteres")
	@NotBlank(message = "O login é obrigatório")
	@EqualsAndHashCode.Include
	@Column(name = "login")
	private String login;
	
	@Size(min = 4, max = 50, message = "O nome deve possuir entre 4 e 50 caracteres")
	@NotBlank(message = "O nome é obrigatório")	
	@Column(name = "nome")
	private String nome;
	
	@NotBlank(message = "A senha do usuario é obrigatório")
	@Column(name = "senha")	
	private String senha;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O papel do usuario é obrigatório")
	@Column(name = "papel")
	private Papel papel;
	
	public Usuario() {
		this.papel = papel.CLIENTE;
	}
	
	public Usuario(
			String login,String nome,String senha, Papel papel) {
		
		this.login = login;
		this.nome = nome;
		this.senha = senha;
		this.papel = papel;
	}
	


	

	
	
	
	
}
