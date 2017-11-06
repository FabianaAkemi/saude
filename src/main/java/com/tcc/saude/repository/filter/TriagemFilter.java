package com.tcc.saude.repository.filter;

import java.time.LocalDate;

public class TriagemFilter {

	private String namePaciente;
	private String cpfPaciente;
	
	private LocalDate desde;	
	private LocalDate ate;

	
	public String getNamePaciente() {
		return namePaciente;
	}
	public void setNamePaciente(String namePaciente) {
		this.namePaciente = namePaciente;
	}
	public String getCpfPaciente() {
		return cpfPaciente;
	}
	public void setCpfPaciente(String cpfPaciente) {
		this.cpfPaciente = cpfPaciente;
	}
	public LocalDate getDesde() {
		return desde;
	}
	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}
	public LocalDate getAte() {
		return ate;
	}
	public void setAte(LocalDate ate) {
		this.ate = ate;
	}
	
	public String getRemoveFormatacao(){
		return this.cpfPaciente = this.cpfPaciente.replaceAll("\\.|-|", "");
	}
	
	
}
