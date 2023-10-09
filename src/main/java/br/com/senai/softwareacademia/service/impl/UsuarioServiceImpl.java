package br.com.senai.softwareacademia.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.softwareacademia.entity.Usuario;
import br.com.senai.softwareacademia.repository.UsuariosRepository;
import br.com.senai.softwareacademia.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuariosRepository repository;
	
	@Override
	public Usuario salvar(Usuario usuario) {
		
		String senhaCript = gerarHashDa(usuario.getSenha());
		
		
		Usuario outroUsuario = repository.buscarPor(usuario.getLogin());
		if (outroUsuario != null) {			
				
				Preconditions.checkArgument(!outroUsuario.equals(usuario), "O usuario ja existe");
			
		}
		
		Usuario usuarioNovo = new Usuario(usuario.getLogin(), usuario.getNome(), senhaCript);
		Usuario usuarioSalvo = repository.save(usuarioNovo);
		
		return usuarioSalvo;
	}

	@Override
	public Usuario buscarPor(String nome) {
		Usuario usuarioEncontrado = repository.buscarPor(nome);
		Preconditions.checkNotNull(usuarioEncontrado, "Não foi encontrado usuario para o nome informado");
		return usuarioEncontrado;
	}
	
	private String gerarHashDa(String senha) {
		return new DigestUtils(MessageDigestAlgorithms.SHA3_256).digestAsHex(senha);
		
	}
	

}
