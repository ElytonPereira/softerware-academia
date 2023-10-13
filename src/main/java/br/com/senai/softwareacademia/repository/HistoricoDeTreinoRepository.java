package br.com.senai.softwareacademia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.softwareacademia.entity.HistoricoDeTreino;

@Repository
public interface HistoricoDeTreinoRepository extends JpaRepository<HistoricoDeTreino, Integer> {

	@Query(value = "SELECT h "
				 + "FROM HistoricoDeTreino h "
				 + "JOIN FETCH h.usuario u "
				 + "WHERE h.usuario = :login")
	public HistoricoDeTreino buscarHistoricoPor(String login);
	
}
