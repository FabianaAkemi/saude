package com.tcc.saude.model;

public enum TipoEspecialista {

	CLINICO ("Clinico"),
    PEDIATRA ("Pediatra") ,
    GINICOLOGISTA ("Ginicologista"),
    CARDIOLOGISTA ("Cardiologista"),
    NEUROLOGISTA ("Neurologista"),
    OFTAMOLOGISTA ("Oftamologista"),
    ORTOPEDISTA ("Ortopedista");
    
    private String tipo;
	
	TipoEspecialista(String tipo){
		this.tipo= tipo;
		
	}
	public String getTipo() {
        return tipo;
    }

  
}
