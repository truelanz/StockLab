-- Employees
INSERT INTO tb_employee (name) VALUES ('Jeca Gay');
INSERT INTO tb_employee (name) VALUES ('Mula Fazueli');
INSERT INTO tb_employee (name) VALUES ('Merab');
INSERT INTO tb_employee (name) VALUES ('Poatan');
INSERT INTO tb_employee (name) VALUES ('Manoel Gomes');

-- Categorias existentes
INSERT INTO tb_category (name) VALUES ('Reagentes Quimicos');
INSERT INTO tb_category (name) VALUES ('Equipamentos de Analise');
INSERT INTO tb_category (name) VALUES ('Materiais de Consumo');
INSERT INTO tb_category (name) VALUES ('Medicamentos Controlados');
INSERT INTO tb_category (name) VALUES ('Equipamentos de Protecao Individual');
INSERT INTO tb_category (name) VALUES ('Vidrarias');
INSERT INTO tb_category (name) VALUES ('Solucoes Padrao');

-- Produtos existentes
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product, validity) VALUES ('Reagente X', 100, 250.75, DATE '2025-09-01', 1, 'https://cdn.awsli.com.br/600x700/1280/1280428/produto/51231978/reagente-neon--3--m8vpuv8bn3.png', DATE '2027-09-01');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Microscopio Optico', 5, 3500.00, DATE '2025-08-15', 2, 'https://t4.ftcdn.net/jpg/01/56/06/33/360_F_156063380_4M6zRW5xpiO1f0Y2FKyugvKel61PbbuW.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Tubos de Ensaio', 200, 2.50, DATE '2025-09-05', 3, 'https://t4.ftcdn.net/jpg/01/56/06/33/360_F_156063380_4M6zRW5xpiO1f0Y2FKyugvKel61PbbuW.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Luva de Latex', 500, 0.75, DATE '2025-09-06', 5, 'https://t4.ftcdn.net/jpg/01/56/06/33/360_F_156063380_4M6zRW5xpiO1f0Y2FKyugvKel61PbbuW.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Oculos de Protecao', 80, 15.00, DATE '2025-09-06', 5, 'https://t4.ftcdn.net/jpg/01/56/06/33/360_F_156063380_4M6zRW5xpiO1f0Y2FKyugvKel61PbbuW.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Bico de Bunsen', 25, 120.00, DATE '2025-09-07', 6, 'https://t4.ftcdn.net/jpg/01/56/06/33/360_F_156063380_4M6zRW5xpiO1f0Y2FKyugvKel61PbbuW.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Pipeta Graduada', 150, 8.50, DATE '2025-09-07', 6, 'https://t4.ftcdn.net/jpg/01/56/06/33/360_F_156063380_4M6zRW5xpiO1f0Y2FKyugvKel61PbbuW.jpg');

INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Solucao Padrao de Cloreto', 40, 28.00, DATE '2025-09-08', 7, 'https://img.com/cloreto.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Espectrofotometro', 3, 12000.00, DATE '2025-09-08', 2, 'https://img.com/espectro.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Centrifuga de Bancada', 4, 7500.00, DATE '2025-09-09', 2, 'https://img.com/centrifuga.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Alcool Etilico 70%', 100, 12.50, DATE '2025-09-09', 1, 'https://img.com/alcool.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Cloroformio', 30, 150.00, DATE '2025-09-10', 1, 'https://img.com/cloroformio.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Mascara N95', 200, 5.50, DATE '2025-09-10', 5, 'https://img.com/mascara.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Avental de PVC', 60, 25.00, DATE '2025-09-11', 5, 'https://img.com/avental.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Erlenmeyer 250ml', 100, 6.00, DATE '2025-09-11', 6, 'https://img.com/erlenmeyer.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Balao Volumetrico 500ml', 80, 12.00, DATE '2025-09-12', 6, 'https://img.com/balao.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Reagente Y', 70, 180.00, DATE '2025-09-12', 1, 'https://img.com/reagenteY.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Reagente Z', 55, 300.00, DATE '2025-09-13', 1, 'https://img.com/reagenteZ.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Solucao Padrao de Sodio', 35, 45.00, DATE '2025-09-13', 7, 'https://img.com/sodio.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Solucao Padrao de Potassio', 25, 50.00, DATE '2025-09-14', 7, 'https://img.com/potassio.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Termometro Digital', 40, 95.00, DATE '2025-09-14', 2, 'https://img.com/termometro.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Estufa de Secagem', 2, 5000.00, DATE '2025-09-15', 2, 'https://img.com/estufa.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Placa de Petri', 500, 1.20, DATE '2025-09-15', 3, 'https://img.com/petri.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Agitador Magnetico', 10, 450.00, DATE '2025-09-16', 2, 'https://img.com/agitador.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Soro Fisiologico 0,9%', 150, 3.00, DATE '2025-09-16', 4, 'https://img.com/soro.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Antibiotico A', 90, 45.00, DATE '2025-09-17', 4, 'https://img.com/antibiotico.jpg');
INSERT INTO tb_product (name, current_quantity, product_value, issuance_date, category_id, img_product) VALUES ('Analgesico B', 120, 12.00, DATE '2025-09-17', 4, 'https://img.com/analgesico.jpg');

-- Clientes existentes
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Ana Silva', DATE '1990-05-10', '11988887777', TIMESTAMP '2025-09-01 09:00:00', 'Rua Paprica Doce, 123', '11111111111');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Joao da Silva', DATE '1985-03-08', '11999996666', TIMESTAMP '2025-09-02 09:10:00', 'Rua Paprica Defumanda, 321', '22222222222');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Maria Oliveira', DATE '1992-07-20', '11970001234', TIMESTAMP '2025-09-03 10:00:00', 'Rua Flores, 10', '33333333333');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Carlos Pereira', DATE '1978-01-15', '11981112222', TIMESTAMP '2025-09-03 10:10:00', 'Av. Central, 55', '44444444444');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Juliana Santos', DATE '1988-11-30', '11992223333', TIMESTAMP '2025-09-03 10:20:00', 'Rua das Palmeiras, 200', '55555555555');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Ricardo Gomes', DATE '1995-04-18', '11993334444', TIMESTAMP '2025-09-04 09:00:00', 'Rua Azul, 77', '66666666666');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Fernanda Costa', DATE '1991-09-09', '11994445555', TIMESTAMP '2025-09-04 09:10:00', 'Rua Verde, 88', '77777777777');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Lucas Martins', DATE '1980-02-02', '11995556666', TIMESTAMP '2025-09-04 09:20:00', 'Rua Amarela, 99', '88888888888');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Patricia Lima', DATE '1987-03-12', '11996667777', TIMESTAMP '2025-09-04 09:30:00', 'Rua Branca, 11', '99999999999');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Eduardo Souza', DATE '1993-05-25', '11997778888', TIMESTAMP '2025-09-05 08:00:00', 'Rua Cinza, 22', '10101010101');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Camila Fernandes', DATE '1994-06-06', '11998889999', TIMESTAMP '2025-09-05 08:10:00', 'Rua Rosa, 33', '12121212121');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Thiago Rocha', DATE '1996-07-07', '11991110000', TIMESTAMP '2025-09-05 08:20:00', 'Rua Roxa, 44', '13131313131');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Beatriz Almeida', DATE '1989-08-08', '11992221111', TIMESTAMP '2025-09-06 07:00:00', 'Rua Prata, 55', '14141414141');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Gustavo Azevedo', DATE '1975-09-09', '11993332222', TIMESTAMP '2025-09-06 07:10:00', 'Rua Ouro, 66', '15151515151');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Mariana Dias', DATE '1990-10-10', '11994443333', TIMESTAMP '2025-09-06 07:20:00', 'Rua Bronze, 77', '16161616161');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Andre Barbosa', DATE '1992-11-11', '11995554444', TIMESTAMP '2025-09-06 07:30:00', 'Rua Ferro, 88', '17171717171');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Aline Ribeiro', DATE '1998-12-12', '11996665555', TIMESTAMP '2025-09-07 10:00:00', 'Rua Niquel, 99', '18181818181');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Rafael Cunha', DATE '1986-01-01', '11997776666', TIMESTAMP '2025-09-07 10:10:00', 'Rua Zinco, 12', '19191919191');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Paula Mendes', DATE '1997-02-02', '11998887777', TIMESTAMP '2025-09-07 10:20:00', 'Rua Chumbo, 23', '20202020202');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Sergio Carvalho', DATE '1983-03-03', '11999998888', TIMESTAMP '2025-09-07 10:30:00', 'Rua Cobre, 34', '21212121212');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Tatiane Lopes', DATE '1981-04-04', '11990009999', TIMESTAMP '2025-09-07 10:40:00', 'Rua Estanho, 45', '22222222222');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Diego Nascimento', DATE '1999-05-05', '11991112222', TIMESTAMP '2025-09-08 08:00:00', 'Rua Quartzo, 56', '23232323232');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Isabela Farias', DATE '1995-06-06', '11992223333', TIMESTAMP '2025-09-08 08:10:00', 'Rua Safira, 67', '24242424242');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Rodrigo Araujo', DATE '1993-07-07', '11993334444', TIMESTAMP '2025-09-08 08:20:00', 'Rua Jade, 78', '25252525252');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Carolina Teixeira', DATE '1992-08-08', '11994445555', TIMESTAMP '2025-09-08 08:30:00', 'Rua Perola, 89', '26262626262');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Fabio Correia', DATE '1984-09-09', '11995556666', TIMESTAMP '2025-09-08 08:40:00', 'Rua Onix, 90', '27272727272');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Natalia Monteiro', DATE '1991-10-10', '11996667777', TIMESTAMP '2025-09-09 09:00:00', 'Rua Cristal, 101', '28282828282');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Claudio Nogueira', DATE '1990-11-11', '11997778888', TIMESTAMP '2025-09-09 09:10:00', 'Rua Rubi, 202', '29292929292');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Vanessa Moreira', DATE '1987-12-12', '11998889999', TIMESTAMP '2025-09-09 09:20:00', 'Rua Esmeralda, 303', '30303030303');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Henrique Batista', DATE '1996-01-15', '11999990000', TIMESTAMP '2025-09-09 09:30:00', 'Rua Topazio, 404', '31313131313');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Larissa Prado', DATE '1998-02-20', '11990001111', TIMESTAMP '2025-09-09 09:40:00', 'Rua Diamante, 505', '32323232323');
INSERT INTO tb_client (name, birth, phone, date_register, local_address, CPF) VALUES ('Felipe Rezende', DATE '1982-03-25', '11991112222', TIMESTAMP '2025-09-09 09:50:00', 'Rua Turmalina, 606', '33333333333');

-- Movimentacoes (mantidas)
INSERT INTO tb_movement (quantity, type_entry_exit, issuance_date, observation, product_id) VALUES (50, 'ENTRY', TIMESTAMP '2025-09-01 09:15:00', 'Compra de reagente', 1);
INSERT INTO tb_movement (quantity, type_entry_exit, issuance_date, observation, product_id) VALUES (10, 'EXIT', TIMESTAMP '2025-09-02 11:00:00', 'Uso em analise clinica', 1);
INSERT INTO tb_movement (quantity, type_entry_exit, issuance_date, observation, product_id) VALUES (100, 'ENTRY', TIMESTAMP '2025-09-03 08:30:00', 'Reposicao de tubos de ensaio', 3);
INSERT INTO tb_movement (quantity, type_entry_exit, issuance_date, observation, product_id) VALUES (5, 'EXIT', TIMESTAMP '2025-09-04 15:00:00', 'Uso em exame de rotina', 3);