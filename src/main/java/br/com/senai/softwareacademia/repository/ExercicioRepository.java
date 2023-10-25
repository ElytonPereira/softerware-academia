package br.com.senai.softwareacademia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.softwareacademia.entity.Exercicio;
import br.com.senai.softwareacademia.entity.Treino;
import br.com.senai.softwareacademia.entity.enums.GrupoDoExercicio;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Integer> {

	@Query(value = "SELECT e FROM Exercicio e WHERE e.id = :id")
	public Exercicio ListarPor(Integer id);

	@Query(value = "SELECT t FROM Treino t JOIN FETCH t.exercicios e WHERE t.id = :id", countQuery = "SELECT Count(t) FROM Treino t WHERE t.id = :id")
	public Page<Exercicio> ListarPorTreino(Treino treino, Pageable paginacao);

	@Query(value = "SELECT e FROM Exercicio e WHERE e.grupo = :grupo", countQuery = "SELECT Count(e) FROM Exercicio e WHERE e.grupo = :grupo")
	public Page<Exercicio> ListarPorGrupo(GrupoDoExercicio grupo, Pageable paginacao);

	@Query(value = "SELECT e FROM Exercicio e", countQuery = "SELECT Count(e) FROM Exercicio e")
	public Page<Exercicio> ListarTodos(Pageable paginacao);
	
	

}
