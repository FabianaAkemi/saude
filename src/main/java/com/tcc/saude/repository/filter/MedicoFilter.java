package com.tcc.saude.repository.filter;

import com.tcc.saude.model.TipoEspecialista;

public class MedicoFilter {

	private String nome;
	
	private String crm;
	
	private TipoEspecialista tipoEspecialista;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public TipoEspecialista getTipoEspecialista() {
		return tipoEspecialista;
	}

	public void setTipoEspecialista(TipoEspecialista tipoEspecialista) {
		this.tipoEspecialista = tipoEspecialista;
	}
	
	
	
}
