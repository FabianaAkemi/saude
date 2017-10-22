CREATE TABLE paciente (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    cpf VARCHAR(50) NOT NULL,
    telefone VARCHAR(50) NOT NULL,
    email VARCHAR(50)NOT NULL,
    logradouro VARCHAR(80) NOT NULL,
    numero BIGINT(20) NOT NULL, 
    complemento VARCHAR(50), 
    cep VARCHAR(10) NOT NULL,
    cidade_uf VARCHAR(50) NOT NULL
   
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE medico (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    crm VARCHAR(10) NOT NULL,
   	tipo_especialista VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE triagem (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    paciente_id BIGINT(20) NOT NULL,
    dor_cabeca BOOLEAN NOT NULL,
    articulacao BOOLEAN NOT NULL,
    dor_corpo  BOOLEAN NOT NULL,
    calafrio   BOOLEAN NOT NULL,    
    febre  BOOLEAN NOT NULL,
    temperatura_febre VARCHAR(30), 
    sonolencia   BOOLEAN NOT NULL,
    problema_respiratorio BOOLEAN NOT NULL,
    problema_respiratorio_ex VARCHAR(50),
    problema_neurologico BOOLEAN NOT NULL,
    problema_neurologico_ex VARCHAR(50),
    problema_cardiaco BOOLEAN NOT NULL,
    problema_cardiaco_ex VARCHAR(50),
    renal BOOLEAN NOT NULL,
    motor BOOLEAN NOT NULL,
    dst BOOLEAN NOT NULL,
    dst_ex VARCHAR(50),
    cancer BOOLEAN NOT NULL,
    cancer_ex VARCHAR(50),
    hepatite BOOLEAN NOT NULL,
    diabetes BOOLEAN NOT NULL,
    hipertensao BOOLEAN NOT NULL,
    alergia BOOLEAN NOT NULL,
    alergia_ex VARCHAR(50),
    deficiencia BOOLEAN NOT NULL,
    medicacao BOOLEAN NOT NULL,
    medicacao_ex VARCHAR(50),
    outros BOOLEAN NOT NULL,
    outros_ex VARCHAR(50),    
    data_criacao DATE NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;