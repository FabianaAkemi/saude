package com.tcc.saude.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by akemi on 02/03/17.
 */
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotBlank(message = "CPF é obrigatório")
	private String cpf;

	@NotBlank(message = "Telefone é obrigatório")
	private String telefone;
	
//	@NotBlank 
//	@Column(name = "data_nascimento")
//	private Date dataNascimento;

	@NotBlank(message = "E-mail é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	@NotBlank(message = "Logradouro é obrigatório")
	@Size(max = 50, message = "O tamanho do logradouro deve estar entre 1 e 50")
	private String logradouro;

	@NotNull(message = "Número obrigatório")
	private Long numero;

	private String complemento;

	@NotBlank(message = "CEP é obrigatório")
	private String cep;

	@NotBlank(message = "Cidade/UF é obrigatório")
	@Column(name = "cidade_uf")
	private String cidadeUf;
	
	@PrePersist @PreUpdate
	private void prePersistPreUpdate() {
		this.cpf = this.cpf.replaceAll("\\.|-|", "");
	}
	
	@PostLoad
	private void postLoad(){
		this.cpf = this.cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");

	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidadeUf() {
		return cidadeUf;
	}

	public void setCidadeUf(String cidadeUf) {
		this.cidadeUf = cidadeUf;
	}
	
	public String removeFormatacao(){
		return this.cpf = this.cpf.replaceAll("\\.|-|", "");
	}
//	public Date getDataNascimento() {
//		return dataNascimento;
//	}
//
//	public void setDataNascimento(Date dataNascimento) {
//		this.dataNascimento = dataNascimento;
//	}
	
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
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
