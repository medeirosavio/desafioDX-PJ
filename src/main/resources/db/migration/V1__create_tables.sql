CREATE TABLE integrante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    franquia VARCHAR(255) NOT NULL,
    funcao VARCHAR(255) NOT NULL
);

CREATE TABLE time (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL
);

CREATE TABLE composicao_time (
    id SERIAL PRIMARY KEY,
    id_time INTEGER NOT NULL REFERENCES time(id),
    id_integrante INTEGER NOT NULL REFERENCES integrante(id)
);
