CREATE DATABASE prorio;

CREATE TABLE users (
  username VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(45) NOT NULL ,
  enabled int NOT NULL DEFAULT 1,
  email varchar(60) NOT NULL UNIQUE,
  PRIMARY KEY (username)
);

CREATE TABLE user_profile (
  id_profile SERIAL,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (id_profile),
  CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE courses (
  id_course SERIAL,
  name varchar(45) NOT NULL UNIQUE,
  PRIMARY KEY (id_course)
);

CREATE TABLE disciplines (
  id_discipline SERIAL,
  name varchar(45) NOT NULL,
  id_course INT NOT NULL,
  period int NOT NULL,
  credits int NOT NULL,
  PRIMARY KEY (id_discipline),
  CONSTRAINT fk_user FOREIGN KEY (id_course) REFERENCES courses(id_course)
);

INSERT INTO users(username, password, enabled, email) VALUES ('escantalice', '123', 1, 'emersoncantalicee@gmail.com'),
                                                    ('maoliveira', '123', 1, 'matheusoliveira@gmail.com'),
                                                    ('ebrito', '123', 1, 'ebrito@gmail.com');

INSERT INTO user_profile(username,role) VALUES ('escantalice', 'ROLE_ADMIN'),
                                              ('maoliveira', 'ROLE_DBA'),
                                              ('ebrito', 'ROLE_USER'),
                                              ('escantalice', 'ROLE_USER');

INSERT INTO courses(id_course, name) VALUES (1,'Sistemas de Informação'),(2,'Administração'),(3,'Medicina');

INSERT INTO disciplines(id_course, name, period, credits) VALUES (1, 'Programação 1', 1, 2),
								 (1, 'Programação 2', 2, 2),
								 (1, 'Praticas de Programação', 3, 2),
                                                                 (2, 'Adminstração Básica', 1, 1), 
                                                                 (2, 'Teoria da Adminstração', 1, 2), 
                                                                 (2, 'Analise de recursos', 2, 1),
                                                                 (3, 'Fisiologia', 1, 2),
                                                                 (3, 'Doenças Criticas', 2, 1),
                                                                 (3, 'Farmacologia', 3, 1);



