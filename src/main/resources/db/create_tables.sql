CREATE TABLE tb_produto (
    id BIGINT PRIMARY KEY IDENTITY,
    name NVARCHAR(255),
    description NVARCHAR(255),
    price DECIMAL(10, 2),
    category NVARCHAR(255),
    quantity NUMERIC
);

CREATE TABLE tb_movimentacao_saida (
    id BIGINT PRIMARY KEY IDENTITY,
    product_id BIGINT,
    quantity INT,
    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
        REFERENCES tb_produto(id)
);

CREATE TABLE tb_movimentacao_entrada (
    id BIGINT PRIMARY KEY IDENTITY,
    product_id BIGINT,
    quantity INT,
    CONSTRAINT fk_product_2
        FOREIGN KEY (product_id)
        REFERENCES tb_produto(id)
);

CREATE OR ALTER TRIGGER TGR_SAIDA
ON tb_movimentacao_saida
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE TB_PRODUTO
    SET quantity = quantity - total_quantity
    FROM TB_PRODUTO p
    INNER JOIN (
        SELECT product_id, SUM(quantity) AS total_quantity
        FROM inserted
        GROUP BY product_id
    ) AS aggregated_movements ON p.id = aggregated_movements.product_id;
END;

CREATE OR ALTER TRIGGER TGR_ENTRADA
ON tb_movimentacao_entrada
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE TB_PRODUTO
    SET quantity = quantity + total_quantity
    FROM TB_PRODUTO p
    INNER JOIN (
        SELECT product_id, SUM(quantity) AS total_quantity
        FROM inserted
        GROUP BY product_id
    ) AS aggregated_movements ON p.id = aggregated_movements.product_id;
END;

INSERT INTO tb_movimentacao_saida values
(5, 2),
(5, 10);

INSERT INTO tb_movimentacao_entrada values
(5, 20),
(5,40),
(4,2);

