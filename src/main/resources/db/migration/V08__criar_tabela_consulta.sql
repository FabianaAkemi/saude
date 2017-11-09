CREATE TABLE consulta (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    paciente_id BIGINT(20) NOT NULL,
    medico_id BIGINT(20) NOT NULL,
	usuario_id BIGINT(20) NOT NULL,
	descricao VARCHAR(700) not null,

    data_criacao DATETIME NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (medico_id) REFERENCES medico(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;	