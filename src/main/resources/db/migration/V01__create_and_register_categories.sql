CREATE SEQUENCE category_id_seq;

CREATE TABLE category 
(
	id BIGINT PRIMARY KEY ,
	name VARCHAR(50) NOT NULL
);

INSERT INTO category (id, name) values (nextval('category_id_seq'),'Lazer');
INSERT INTO category (id, name) values (nextval('category_id_seq'), 'Alimentação');
INSERT INTO category (id, name) values (nextval('category_id_seq'), 'Supermercado');
INSERT INTO category (id, name) values (nextval('category_id_seq'), 'Farmacia');
INSERT INTO category (id, name) values (nextval('category_id_seq'), 'Outros');
