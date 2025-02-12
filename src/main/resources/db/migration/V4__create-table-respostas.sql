CREATE TABLE respostas(

    id BIGINT NOT NULL AUTO_INCREMENT,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    solucao BOOLEAN DEFAULT FALSE,
    topico_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_respostas_topico_id FOREIGN KEY(topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respostas_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id)

);