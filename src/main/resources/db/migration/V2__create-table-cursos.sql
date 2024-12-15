CREATE TABLE cursos(

    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    carga_horaria VARCHAR(30) NOT NULL,

    PRIMARY KEY(id)

);