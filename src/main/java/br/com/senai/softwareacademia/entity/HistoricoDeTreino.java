package br.com.senai.softwareacademia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Table(name = "historicoDeTreino")
@Entity(name = "HistoricoDeTreino")
@AllArgsConstructor
public class HistoricoDeTreino {

	@Size(min = 2, max = 50, message = "O peso deve ser entre 2 e 50 Kilogramas")
	@NotNull(message = "O peso é obrigatório")
	@Column(name = "peso")
	private int peso;
	
	@NotNull(message = "O usuário é obrigatório")
	@OneToMany(fetch = FetchType.LAZY)
	@Column(name = "id_usuario")
	private Usuario usuario;
	
	public HistoricoDeTreino() {}
	
}
