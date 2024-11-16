ALTER TABLE categoria
    ADD CONSTRAINT uc_categoria_nome UNIQUE (nome);

ALTER TABLE categoria
    ALTER COLUMN nome SET NOT NULL;

ALTER TABLE produto
DROP
COLUMN status;

ALTER TABLE produto
    ADD status BOOLEAN;

ALTER TABLE produto
    ALTER COLUMN valor DROP NOT NULL;