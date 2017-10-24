CREATE TABLE colaborador (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    rg VARCHAR(20) NOT NULL,
   	data_nascimento DATE NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    atividade_exercida VARCHAR(20) NOT NULL    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;