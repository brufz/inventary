CREATE TABLE tb_produto (
    id BIGINT PRIMARY KEY IDENTITY,
    name NVARCHAR(255),
    description NVARCHAR(255),
    price DECIMAL(10, 2),
    category NVARCHAR(255),
    quantity NUMERIC
);

CREATE TABLE tb_movimentacao (
    id BIGINT PRIMARY KEY IDENTITY,
    product_id BIGINT,
    quantity INT,
    type_mov NVARCHAR(7) CHECK (type_mov IN('ENTRADA','SAIDA')),
    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
        REFERENCES tb_produto(id)
);

INSERT INTO tb_movimentacao values
(1, 20, 'ENTRADA'),
(2, 15, 'ENTRADA'),
(3, 2, 'SAIDA'),
(3, 3, 'ENTRADA');

