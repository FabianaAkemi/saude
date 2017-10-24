package com.tcc.saude.repository.filter;

import com.tcc.saude.model.AtividadeExercida;

public class ColaboradorFilter {
	
	private String nome;
	
	private String rg;
	
	private AtividadeExercida atividadeExercida;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	

	public AtividadeExercida getAtividadeExercida() {
		return atividadeExercida;
	}

	public void setAtividadeExercida(AtividadeExercida atividadeExercida) {
		this.atividadeExercida = atividadeExercida;
	}

	
	

}
