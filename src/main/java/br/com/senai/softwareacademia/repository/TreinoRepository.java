package br.com.senai.softwareacademia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.softwareacademia.entity.Treino;
import br.com.senai.softwareacademia.entity.enums.ObjetivoDoTreino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Integer> {


	@Query(value = "SELECT t FROM Treino t JOIN FETCH t.exercicios e WHERE t.id = :id")
	public Treino ListarPor(Integer id);  
	
	@Query(value = "SELECT t FROM Treino t JOIN FETCH t.exercicios e WHERE t.objetivo = :objetivo",
			countQuery = "SELECT Count(t) FROM Treino t WHERE t.objetivo = :objetivo")
	public Page<Treino> ListarPor(ObjetivoDoTreino objetivo, Pageable paginacao);  
	
	
	
	
}
