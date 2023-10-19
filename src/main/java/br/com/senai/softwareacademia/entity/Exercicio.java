package br.com.senai.softwareacademia.entity;

import br.com.senai.softwareacademia.entity.enums.GrupoDoExercicio;
import br.com.senai.softwareacademia.entity.enums.ObjetivoDoTreino;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "exercicio")
@Entity(name = "Exercicio")
@AllArgsConstructor
public class Exercicio {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Size(max = 50, message = "A descrição não pode ter mais de 50 caracteres")
	@NotBlank(message = "A descrição é obrigatória")
	@Column(name = "descricao")
	private String descricaoExercicio;

	@NotBlank(message = "A duração do descanso é obrigatória")
	@Column(name = "descanso")
	private int duracaoDescanso;

	@NotBlank(message = "A quantidade de repetições é obrigatória")
	@Column(name = "repeticoes")
	private int qtdRepeticoes;

	@NotBlank(message = "A quantidade de series é obrigatória")
	@Column(name = "series")
	private int series;

	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O tipo do exercicio é obrigatório!")
	@Column(name = "grupo")
	private GrupoDoExercicio grupo;	
	
	public Exercicio (Treino treino) {
		this.duracaoDescanso = calcularTempoDeDescanso(treino.getObjetivo());
	
	}
	

	@Override
	public String toString() {
		return "\n Exercicio: " + descricaoExercicio + "\n Descanso: " + duracaoDescanso + " sec" + "\n Repetições: "
				+ qtdRepeticoes + "\n Series: " + series + "\n Treino: " + grupo;
	}

	public static int calcularTempoDeDescanso(ObjetivoDoTreino objetivo) {
		int tempoDescanso = 0;

		switch (objetivo) {
		case HIPERTROFIA:
			tempoDescanso = 90;
			break;
		case FORCA:
			tempoDescanso = 180;
			break;
		case RESISTENCIA:
			tempoDescanso = 30;
			break;
		case PERDA:
			tempoDescanso = 30;
			break;
		default:
			throw new IllegalArgumentException("É preciso passar o objetivo do treino!");
			
		}

		return tempoDescanso;
	}

}
