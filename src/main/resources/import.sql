-- Categorias
INSERT INTO tb_category (name) VALUES ('Reagentes Quimicos');
INSERT INTO tb_category (name) VALUES ('Equipamentos de Analise');
INSERT INTO tb_category (name) VALUES ('Materiais de Consumo');

-- Produtos
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id) VALUES ('Reagente X', 100, 250.75, DATE '2025-09-01', 1);

INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id) VALUES ('Microscopio Optico', 5, 3500.00, DATE '2025-08-15', 2);

INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id) VALUES ('Tubos de Ensaio', 200, 2.50, DATE '2025-09-05', 3);

-- Clientes
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Ana Silva', DATE '1990-05-10', '11988887777', TIMESTAMP '2025-09-01 09:00:00', 'Rua Páprica Doce, 123', '11111111111');

INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Joao da Silva', DATE '1985-03-08', '11999996666', TIMESTAMP '2025-09-02 09:10:00', 'Rua Páprica Defumanda, 321', '22222222222');

-- Movimentações (type_entry_exit = 'ENTRY' / 'EXIT')
INSERT INTO tb_movement (quantity, type_entry_exit, issuance_date, observation, product_id) VALUES (50, 'ENTRY', TIMESTAMP '2025-09-01 09:15:00', 'Compra de reagente', 1);

INSERT INTO tb_movement (quantity, type_entry_exit, issuance_date, observation, product_id) VALUES (10, 'EXIT', TIMESTAMP '2025-09-02 11:00:00', 'Uso em análise clínica', 1);

INSERT INTO tb_movement (quantity, type_entry_exit, issuance_date, observation, product_id) VALUES (100, 'ENTRY', TIMESTAMP '2025-09-03 08:30:00', 'Reposição de tubos de ensaio', 3);

INSERT INTO tb_movement (quantity, type_entry_exit, issuance_date, observation, product_id) VALUES (5, 'EXIT', TIMESTAMP '2025-09-04 15:00:00', 'Uso em exame de rotina', 3);
