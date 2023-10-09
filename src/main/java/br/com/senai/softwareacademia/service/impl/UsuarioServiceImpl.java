package br.com.senai.softwareacademia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.softwareacademia.entity.Usuario;
import br.com.senai.softwareacademia.repository.UsuariosRepository;
import br.com.senai.softwareacademia.service.UsuarioService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuariosRepository repository;
	
	@Override
	public Usuario salvar(Usuario usuario) {
		Usuario outroUsuario = repository.buscarPor(usuario.getLogin());
		if (outroUsuario != null) {
			if (usuario.isPersistido()) {
				Preconditions.checkArgument(outroUsuario.equals(usuario), "O usuario ja existe");
			}
		}
		
		Usuario usuarioSalvo = repository.save(usuario);
		
		return usuarioSalvo;
	}

	@Override
	public Usuario buscarPor(String nome) {
		Usuario usuarioEncontrado = repository.buscarPor(nome);
		Preconditions.checkNotNull(usuarioEncontrado, "Não foi encontrado usuario para o nome informado");
		return usuarioEncontrado;
	}

}
