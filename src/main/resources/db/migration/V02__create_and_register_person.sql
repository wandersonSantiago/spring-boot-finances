CREATE SEQUENCE person_id_seq;

CREATE TABLE person 
(
	id BIGINT PRIMARY KEY ,
	name VARCHAR(50) NOT NULL,
	status BOOLEAN NOT NULL,
	
	street VARCHAR(50),
	complement VARCHAR(50),
	district VARCHAR(50),
	zip_code VARCHAR(50),
	city VARCHAR(50),
	state VARCHAR(50),
	country VARCHAR(50),
	number VARCHAR(50)
);

INSERT INTO person (id, name, status, street, complement, district, zip_code, city, state , country, number) 
values (nextval('person_id_seq'),'Maria da Silva', true, 'Rua Andorinhas', 'perto da padaria', 'Bairro das nações', '16800-000', 'Andradina', 'São Paulo',
'Brasil', '567');

INSERT INTO person (id, name, status, street, complement, district, zip_code, city, state , country, number) 
values (nextval('person_id_seq'),'José Emanuel da Silva', true, 'Rua dos Pombos', 'perto da sorveteria', 'Bairro das Aves', '80800-000', 'Baixada  Fluminense', 'Rio de Janeiro',
'Brasil', '56');