CREATE SEQUENCE entry_id_seq;

CREATE TABLE entry (
	id BIGINT PRIMARY KEY ,
	decription VARCHAR NOT NULL,
	date_expiry DATE NOT NULL,
	date_payment DATE,
	amount DECIMAL  NOT NULL,
	observation VARCHAR ,
	types VARCHAR NOT NULL,
	id_category BIGINT NOT NULL,
	id_person BIGINT NOT NULL,
	FOREIGN KEY (id_category) REFERENCES category(id),
	FOREIGN KEY (id_person) REFERENCES person(id)
);

INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Top Club', '2017-06-10', null, 120, null, 'RECEITA', 3, 3);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA', 3, 4);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'DMAE', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 5);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEITA', 4, 6);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Bahamas', '2017-06-10', null, 500, null, 'RECEITA', 1, 7);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 8);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Despachante', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 9);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEITA', 5, 10);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Café', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 5);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Instrumentos', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 3);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 2);
INSERT INTO entry (id, decription, date_expiry, date_payment, amount, observation, types, id_category, id_person) values (nextval('entry_id_seq'), 'Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);