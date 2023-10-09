package br.com.senai.softwareacademia.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.senai.softwareacademia.entity.Usuario;
import br.com.senai.softwareacademia.service.UsuarioService;

@Service
public class UsuarioServiceProxy implements UsuarioService {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService service;
	
	@Override
	public Usuario salvar(Usuario usuario) {
		
		return service.salvar(usuario);
	}

	@Override
	public Usuario buscarPor(String nome) {
		
		return service.buscarPor(nome);
	}

}
