CREATE SEQUENCE user_id_seq;
CREATE SEQUENCE permission_id_seq;

CREATE TABLE tb_user (
	id BIGINT PRIMARY KEY,
	name VARCHAR NOT NULL,
	email VARCHAR NOT NULL,
	password VARCHAR NOT NULL
); 

CREATE TABLE permission (
	id BIGINT PRIMARY KEY,
	description VARCHAR NOT NULL
);

CREATE TABLE user_permission (
	id_user BIGINT NOT NULL,
	id_permission BIGINT NOT NULL,
	PRIMARY KEY (id_user, id_permission),
	FOREIGN KEY (id_user) REFERENCES tb_user(id),
	FOREIGN KEY (id_permission) REFERENCES permission(id)
);

INSERT INTO tb_user (id, name, email, password) values (nextval('user_id_seq'), 'Administrador', 'admin@finances.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO tb_user (id, name, email, password) values (nextval('user_id_seq'), 'Maria Silva', 'maria@finances.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permission (id, description) values (nextval('permission_id_seq'), 'ROLE_CREATE_CATEGORY');
INSERT INTO permission (id, description) values (nextval('permission_id_seq'), 'ROLE_SEARCH_CATEGORY');

INSERT INTO permission (id, description) values (nextval('permission_id_seq'), 'ROLE_CREATE_PERSON');
INSERT INTO permission (id, description) values (nextval('permission_id_seq'), 'ROLE_REMOVE_PERSON');
INSERT INTO permission (id, description) values (nextval('permission_id_seq'), 'ROLE_SEARCH_PERSON');

INSERT INTO permission (id, description) values (nextval('permission_id_seq'), 'ROLE_CREATE_ENTRY');
INSERT INTO permission (id, description) values (nextval('permission_id_seq'), 'ROLE_REMOVE_ENTRY');
INSERT INTO permission (id, description) values (nextval('permission_id_seq'), 'ROLE_SEARCH_ENTRY');

-- admin
INSERT INTO user_permission (id_user, id_permission) values (1, 1);
INSERT INTO user_permission (id_user, id_permission) values (1, 2);
INSERT INTO user_permission (id_user, id_permission) values (1, 3);
INSERT INTO user_permission (id_user, id_permission) values (1, 4);
INSERT INTO user_permission (id_user, id_permission) values (1, 5);
INSERT INTO user_permission (id_user, id_permission) values (1, 6);
INSERT INTO user_permission (id_user, id_permission) values (1, 7);
INSERT INTO user_permission (id_user, id_permission) values (1, 8);

-- maria
INSERT INTO user_permission (id_user, id_permission) values (2, 2);
INSERT INTO user_permission (id_user, id_permission) values (2, 5);
INSERT INTO user_permission (id_user, id_permission) values (2, 8);