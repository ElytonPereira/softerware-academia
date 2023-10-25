package br.com.senai.softwareacademia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.senai.softwareacademia.entity.Exercicio;
import br.com.senai.softwareacademia.entity.Treino;
import br.com.senai.softwareacademia.entity.enums.GrupoDoExercicio;
import br.com.senai.softwareacademia.entity.enums.ObjetivoDoTreino;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface ExercicioService {
	
	public Exercicio inserir(
			@Valid
			@NotNull(message = "O exercicio é obrigatório!")
			Exercicio exercicio);
	

	public Exercicio alterar(
			@Valid
			@NotNull(message = "O exercicio salvo é obrigatório!")
			Exercicio exercicioSalvo);
	
	public Page<Exercicio> listarPorGrupo(
			@NotNull(message = "O objetivo do treino é obrigatório")
			GrupoDoExercicio grupo, Pageable paginacao);
	
	
	public Page<Exercicio> listarPorTreino (
			@NotNull(message = "O treino é obrigatório!")
			Integer idDotreino, Pageable paginacao);
	
	public Exercicio buscarPor(
			@NotNull(message = "O id é obrigatório!")
			@Positive(message = "O id deve ser possitivo!")
			Integer id);
	
}
	
	
	
	

