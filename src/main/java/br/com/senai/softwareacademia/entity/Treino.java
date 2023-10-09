package br.com.senai.softwareacademia.entity;

import java.util.Date;
import java.util.List;

import br.com.senai.softwareacademia.entity.enums.TipoDeTreino;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "treino")
@Entity(name = "Treino")
@AllArgsConstructor
public class Treino {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank(message = "A descrição do treino é obrigatória")
	@Column(name = "descricao")
	private String descricao;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "O exercicio é obrigatório")
	@JoinColumn(name = "id_exercicio")
	private List<Exercicio> exercicios;
	
	@NotBlank(message = "A duração é obrigatória")
	@Column(name = "duracao")
	private Date duracao;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O tipo do trino é obrigatório!")
	@Column(name = "tipo")
	private TipoDeTreino tipo;

	@Override
	public String toString() {
		return "Treino - " + descricao + "\n" + exercicios.toString() + "\n, Duração:" + duracao + ", Tipo:" + tipo;
	}
	
	
	
}
