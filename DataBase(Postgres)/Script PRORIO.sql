CREATE DATABASE prorio;

CREATE TYPE mode AS ENUM ('0','1');
CREATE TYPE function_user AS ENUM ('teacher','coordinator');

CREATE TABLE people(
	id_people SERIAL,
	name varchar(100) NOT NULL,
	birth_date Date NOT NULL,
	cpf varchar(13) NOT NULL,
	CONSTRAINT pk_people PRIMARY KEY (id_people)
);

CREATE TABLE officials(
  id_employee SERIAL,
  id_people int,
  record_number varchar(13) NOT NULL,
  CONSTRAINT pk_employee PRIMARY KEY (id_employee),
  CONSTRAINT fk_employee_people FOREIGN KEY (id_people) REFERENCES people (id_people) ON DELETE CASCADE
);


CREATE TABLE functions(
	id_employee int,
	function function_user, 
	CONSTRAINT fk_employee FOREIGN KEY (id_employee) REFERENCES officials (id_employee) ON DELETE CASCADE
);


CREATE TABLE availability(
	id_employee int UNIQUE,
	schedule_one mode NOT NULL,
	schedule_two mode NOT NULL,
	schedule_three mode NOT NULL,
	schedule_four mode NOT NULL,
	schedule_five mode NOT NULL,
	schedule_six mode NOT NULL,
	CONSTRAINT fk_employee FOREIGN KEY (id_employee) REFERENCES officials (id_employee) ON DELETE CASCADE
);


CREATE TABLE users (
  id_employee int,
  username VARCHAR(45),
  password VARCHAR(45) NOT NULL ,
  enabled int NOT NULL DEFAULT 1,
  email varchar(60) NOT NULL UNIQUE,
  CONSTRAINT pk_username PRIMARY KEY (username),
  CONSTRAINT fk_user_employee FOREIGN KEY (id_employee) REFERENCES officials (id_employee) ON DELETE CASCADE
);


CREATE TABLE user_profile (
  id_profile SERIAL,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (id_profile),
  CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);


CREATE TABLE courses (
  id_course SERIAL,
  name varchar(45) NOT NULL UNIQUE,
  PRIMARY KEY (id_course)
);


CREATE TABLE disciplines (
  id_discipline SERIAL,
  name varchar(45) NOT NULL,
  id_course int NOT NULL,
  period int NOT NULL,
  credits int NOT NULL,
  PRIMARY KEY (id_discipline),
  CONSTRAINT fk_user FOREIGN KEY (id_course) REFERENCES courses(id_course) ON DELETE CASCADE
);


CREATE TABLE employee_courses(
	id_employee_course SERIAL,
	id_employee int,
	id_course int,
	CONSTRAINT pk_employee_course PRIMARY KEY (id_employee_course),
	CONSTRAINT fk_employee_ref FOREIGN KEY (id_employee) REFERENCES officials (id_employee) ON DELETE CASCADE,
	CONSTRAINT fk_course FOREIGN KEY (id_course) REFERENCES courses (id_course) ON DELETE CASCADE
);


CREATE TABLE students(
	id_student SERIAL,
	id_people int,
	registration varchar(13)not null,
	CONSTRAINT pk_student PRIMARY KEY (id_student),
	CONSTRAINT fk_student FOREIGN KEY (id_student) REFERENCES people (id_people) ON DELETE CASCADE
);



CREATE TABLE students_courses(
	id_student_course SERIAL,
	id_student int not null,
	id_course int not null,
	CONSTRAINT pk_student_course PRIMARY KEY (id_student_course),
	CONSTRAINT fk_student FOREIGN KEY (id_student) REFERENCES students (id_student) ON DELETE CASCADE,
	CONSTRAINT fk_course FOREIGN KEY (id_course) REFERENCES courses (id_course) ON DELETE CASCADE
);


INSERT INTO people(name,birth_date,cpf) VALUES  ('Emerson','12-12-1991','123456'),
												('Junior','12-12-1991', '132456'),
												('Matheus','12-12-1991', '13245'),
												('Amanda','12-12-1991', '132572'),
												('Fernanda','12-12-1991', '132456'),
												('Leydson','12-12-1991', '13245'),
												('Germano','12-12-1991', '13245'),
												('Alberto','12-12-1991', '132572'); 

INSERT INTO officials(id_people,record_number) VALUES (1,'123456'),(2,'123457'), (3,'123458'), (4,'123958');

INSERT into functions(id_employee,function) values (1,'coordinator'), (2,'teacher'), (3,'teacher'), (4,'teacher');

INSERT INTO availability(id_employee,schedule_one, schedule_two, schedule_three, schedule_four,schedule_five,schedule_six)
	   VALUES(1,'0','1','0','1','0','1'),(2,'1','1','1','1','0','1'),(3,'1','1','0','0','0','1')
	   		  ,(4,'0','0','1','1','0','1');

INSERT INTO users(id_employee,username, password, enabled, email) VALUES (1,'escantalice', '123', 1, 'emersoncantalicee@gmail.com'),
                                                    (2,'maoliveira', '123', 1, 'matheusoliveira@gmail.com'),
                                                    (3,'ebrito', '123', 1, 'ebrito@gmail.com');

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

INSERT INTO employee_courses(id_employee,id_course) VALUES (1,1),(2,2),(3,3),(4,1);

INSERT INTO students(id_people, registration) VALUES (5,'1423080002'),
													 (6,'1423080003'),
													 (7,'1423080004'),
													 (8,'1423080005');

INSERT into students_courses(id_student,id_course) values (1,1),(2,2),(3,3),(4,3);


select people.name,courses.name, students.id_student from people, students, students_courses, courses where students.id_student = students_courses.id_student and
										courses.id_course = students_courses.id_course and
										people.id_people = students.id_student and
										students_courses.id_student = 2;


