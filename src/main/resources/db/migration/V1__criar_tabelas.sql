CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE categoria
(
    id        INTEGER NOT NULL,
    nome      VARCHAR(255),
    descricao VARCHAR(255),
    CONSTRAINT pk_categoria PRIMARY KEY (id)
);

CREATE TABLE imagem_produto
(
    id          INTEGER NOT NULL,
    produto_id  INTEGER,
    url         VARCHAR(255),
    descricao   VARCHAR(255),
    data_upload TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_imagemproduto PRIMARY KEY (id)
);

CREATE TABLE produto
(
    id           INTEGER NOT NULL,
    nome         VARCHAR(255),
    quantidade   INTEGER NOT NULL,
    codigo       VARCHAR(255),
    descricao    VARCHAR(255),
    valor        DOUBLE PRECISION,
    status       BOOLEAN,
    categoria_id INTEGER,
    CONSTRAINT pk_produto PRIMARY KEY (id)
);

ALTER TABLE imagem_produto
    ADD CONSTRAINT FK_IMAGEMPRODUTO_ON_PRODUTO FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE produto
    ADD CONSTRAINT FK_PRODUTO_ON_CATEGORIA FOREIGN KEY (categoria_id) REFERENCES categoria (id);