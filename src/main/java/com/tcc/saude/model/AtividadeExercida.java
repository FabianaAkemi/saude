package com.tcc.saude.model;

public enum AtividadeExercida {
	
	ATENDENTE ("Atendente"),
    ENFERMEIRA ("Enfermeira");

    
    private String atividade;
	
    AtividadeExercida(String atividade){
		this.atividade= atividade;
		
	}
	public String getAtividade() {
        return atividade;
    }

}
