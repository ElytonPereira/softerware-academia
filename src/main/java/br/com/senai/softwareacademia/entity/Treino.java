package br.com.senai.softwareacademia.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.senai.softwareacademia.entity.enums.ObjetivoDoTreino;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "treino")
@Entity(name = "Treino")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
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
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Exercicio.class)
	@JoinTable(name = "treino_exercicio", joinColumns = @JoinColumn(name = "id_treino"), inverseJoinColumns = @JoinColumn(name = "id_exercicio"))
	@NotNull(message = "O exercicio é obrigatório")
	private List<Exercicio> exercicios;

	@NotBlank(message = "A duração é obrigatória")
	@Column(name = "duracao_meses")
	private Integer duracao;

	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O objetivo do treino é obrigatório!")
	@Column(name = "objetivo")
	private ObjetivoDoTreino objetivo;

	public Treino() {
		this.duracao = calcularDuracaoTreino();
		this.exercicios = new ArrayList<Exercicio>();
	}

	@Override
	public String toString() {
		return "Treino - " + descricao + "\n" + exercicios.toString() + "\n, Duração:" + duracao + ", Tipo:" + objetivo;
	}
	
	@Transient
	public Integer calcularDuracaoTreino() {

		LocalDate dataAtual = LocalDate.now();
		LocalDate duracaoTreino = dataAtual.plus(Period.ofMonths(3));

		return duracaoTreino.getMonthValue();
	}

}
