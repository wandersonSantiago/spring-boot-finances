CREATE SEQUENCE category_id_seq;

CREATE TABLE category 
(
	id BIGINT PRIMARY KEY ,
	name VARCHAR(50) NOT NULL
);

INSERT INTO category (id, name) values (1,'Lazer');
INSERT INTO category (id, name) values (2, 'Alimentação');
INSERT INTO category (id, name) values (3, 'Supermercado');
INSERT INTO category (id, name) values (4, 'Farmacia');
INSERT INTO category (id, name) values (5, 'Outros');
