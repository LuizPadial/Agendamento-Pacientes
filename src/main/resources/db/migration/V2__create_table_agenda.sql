CREATE TABLE agenda (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    data_hora TIMESTAMP NULL,
    data_criacao TIMESTAMP NULL,
    paciente_id INT,
    CONSTRAINT fk_paciente
        FOREIGN KEY (paciente_id)
        REFERENCES paciente(id)
        ON DELETE SET NULL
);
