package com.tcc.saude.repository.filter;

public class PacienteFilter {
	
	private String nome;
	private String cpf;

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
	
	public String getRemoveFormatacao(){
		return this.cpf = this.cpf.replaceAll("\\.|-|", "");
	}

}
