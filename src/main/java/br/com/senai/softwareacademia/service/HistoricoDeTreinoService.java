package br.com.senai.softwareacademia.service;

import org.springframework.validation.annotation.Validated;

import br.com.senai.softwareacademia.entity.HistoricoDeTreino;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
public interface HistoricoDeTreinoService {

	
	public HistoricoDeTreino inserir(
			@Valid
			@NotNull(message = "O histórico de treino é obrigatório")
			HistoricoDeTreino historico);
	
}
