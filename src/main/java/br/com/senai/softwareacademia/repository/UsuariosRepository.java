package br.com.senai.softwareacademia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.senai.softwareacademia.entity.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, String>{

	@Query(value = "SELECT u "
			+ "FROM Usuario u "
			+ "WHERE u.login = :login")
	public Usuario buscarPor(String login);
	
}
