-- =====================================================================
-- V2 - Carga inicial de dados (seed) para demonstracao da API
-- =====================================================================

INSERT INTO fornecedor (id, nome, cnpj) VALUES
    (1, 'Torrefacao Grao Nobre',   '52.601.815/0001-20'),
    (2, 'Laticinios Vale Verde',   '90.830.166/0001-28'),
    (3, 'Panificadora Pao & Cia',  '13.186.091/0001-43'),
    (4, 'Distribuidora Doce Sul',  '39.099.603/0001-28');

INSERT INTO produto (nome, preco, quantidade, categoria, fornecedor_id) VALUES
    ('Cafe Expresso',        6.50,  120, 'BEBIDA_QUENTE', 1),
    ('Cappuccino',           11.00,  80, 'BEBIDA_QUENTE', 1),
    ('Chocolate Quente',     12.50,  60, 'BEBIDA_QUENTE', 2),
    ('Suco de Laranja 300ml', 9.00,  45, 'BEBIDA_FRIA',   4),
    ('Iced Latte',           14.00,  35, 'BEBIDA_FRIA',   2),
    ('Bolo de Cenoura',       8.75,  24, 'DOCE',          3),
    ('Brownie',               7.90,  30, 'DOCE',          4),
    ('Pao de Queijo',         5.00, 150, 'SALGADO',       3),
    ('Croissant de Presunto', 13.50, 20, 'SALGADO',       3);
