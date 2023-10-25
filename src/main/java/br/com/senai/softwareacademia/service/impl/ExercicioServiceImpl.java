package br.com.senai.softwareacademia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.softwareacademia.entity.Exercicio;
import br.com.senai.softwareacademia.entity.Treino;
import br.com.senai.softwareacademia.entity.enums.GrupoDoExercicio;
import br.com.senai.softwareacademia.entity.enums.ObjetivoDoTreino;
import br.com.senai.softwareacademia.repository.ExercicioRepository;
import br.com.senai.softwareacademia.repository.TreinoRepository;
import br.com.senai.softwareacademia.service.ExercicioService;

@Service
public class ExercicioServiceImpl implements ExercicioService {
	
	@Autowired
	ExercicioRepository repository;
	
	@Autowired
	TreinoRepository treinoRepository;

	@Override
	public Exercicio inserir( Exercicio exercicio) {
		
		return null;
	}

	@Override
	public Exercicio alterar(Exercicio exercicioSalvo) {
		
		return null;
	}

	@Override
	public Page<Exercicio> listarPor(GrupoDoExercicio grupo, Pageable paginacao) {
		
		Page<Exercicio> exercicios = repository.ListarPorGrupo(grupo, paginacao);
		Preconditions.checkNotNull(exercicios, "Não foi encontrado exercicio para o grupo informado");
		return exercicios;
	}

	@Override
	public Page<Exercicio> listarPor(Treino treino, Pageable paginacao) {
		Treino treinoSalvo = treinoRepository.ListarPor(treino.getId());
		Page<Exercicio> exercicios = repository.ListarPorTreino(treinoSalvo, paginacao);
		return exercicios;
	}

	@Override
	public Exercicio buscarPor(Integer id) {
		Exercicio exercicioSalvo = repository.ListarPor(id);
		Preconditions.checkNotNull(exercicioSalvo, "Não foi encontrado exercicio para o id informado!");
		
		return exercicioSalvo;
	}

}
