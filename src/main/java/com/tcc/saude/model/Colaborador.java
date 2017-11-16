package com.tcc.saude.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "colaborador")
public class Colaborador implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "CPF é obrigatório")
	private String cpf;
	
	@NotBlank(message = "RG é obrigatório")
	private String rg;
	
	@NotNull(message = "Data Nascimento é obrigatório")
	@Column(name="data_nascimento")
	private LocalDate dataNascimento;

	@NotBlank(message = "Telefone é obrigatório")
	private String telefone;
	
	@NotNull(message = "Atividade Exercida é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name="atividade_exercida")
	private AtividadeExercida atividadeExercida;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	public AtividadeExercida getAtividadeExercida() {
		return atividadeExercida;
	}

	public void setAtividadeExercida(AtividadeExercida atividadeExercida) {
		this.atividadeExercida = atividadeExercida;
	}
	
	public boolean isNovo() {
		return id == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colaborador other = (Colaborador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
