package com.tcc.saude.model;

public enum AtividadeExercida {
	
	ATENDENTE ("Atendente"),
    AUXILIAR_ADM ("Auxiliar administrativo"),
    ENFERMEIRA ("Enfermeira"),
    ENFERMEIRO_LIDER ("Lider enfermagem"),
    ASSISTENTE_SOCIAL ("Assistente Social");    

    
    private String atividade;
	
    AtividadeExercida(String atividade){
		this.atividade= atividade;
		
	}
	public String getAtividade() {
        return atividade;
    }

}
