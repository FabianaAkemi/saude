CREATE TABLE paciente (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    cpf VARCHAR(50) NOT NULL,
    telefone VARCHAR(50) NOT NULL,
    email VARCHAR(50)NOT NULL,
    logradouro VARCHAR(80) NOT NULL,
    numero BIGINT(20) NOT NULL, 
    complemento VARCHAR(50), 
    cep BIGINT(20) NOT NULL,
    cidade_uf VARCHAR(50) NOT NULL
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE medico (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    crm BIGINT(20) NOT NULL,
   	tipo_especialista VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE triagem (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    paciente_id BIGINT(20) NOT NULL,
    dor_cabeca TINYINT(1) NOT NULL,
    articulacao TINYINT(1) NOT NULL,
    dor_corpo  TINYINT(1) NOT NULL,
    calafrio   TINYINT(1) NOT NULL,    
    febre  TINYINT(1) NOT NULL,
    temperatura_febre VARCHAR(30), 
    sonolencia   TINYINT(1) NOT NULL,
    problema_respiratorio TINYINT(1) NOT NULL,
    problema_respiratorio_ex VARCHAR(50),
    problema_neurologico TINYINT(1) NOT NULL,
    problema_neurologico_ex VARCHAR(50),
    problema_cardiaco TINYINT(1) NOT NULL,
    problema_cardiaco_ex VARCHAR(50),
    renal TINYINT(1) NOT NULL,
    motor TINYINT(1) NOT NULL,
    dst TINYINT(1) NOT NULL,
    dst_ex VARCHAR(50),
    cancer TINYINT(1) NOT NULL,
    cancer_ex VARCHAR(50),
    hepatite TINYINT(1) NOT NULL,
    diabetes TINYINT(1) NOT NULL,
    hipertensao TINYINT(1) NOT NULL,
    alergia TINYINT(1) NOT NULL,
    alergia_ex VARCHAR(50),
    deficiencia TINYINT(1) NOT NULL,
    medicacao TINYINT(1) NOT NULL,
    medicacao_ex VARCHAR(50),
    outros TINYINT(1) NOT NULL,
    outros_ex VARCHAR(50),
    data_criacao DATE NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;