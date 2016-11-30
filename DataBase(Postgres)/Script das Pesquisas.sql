--disciplinas dos alunos

select disciplines.name,courses.name, people.name from people, students, students_courses, courses, disciplines 
										where students.id_student = students_courses.id_student and
										courses.id_course = students_courses.id_course and
										people.id_people = students.id_people and
									disciplines.id_course = courses.id_course;


--disponibilidade dos professores

select people.name,availability.Day_of_the_week,availability.Day_of_the_week ,availability.schedule_one, availability.schedule_two, 
availability.schedule_three, availability.schedule_four,availability.schedule_five,availability.schedule_six  
										from people, officials, functions, availability 
										where people.id_people = officials.id_people and
										officials.id_employee = functions.id_employee and
										availability.id_employee = officials.id_employee;

--disciplinas alunos e professores

select disciplines.name,courses.name, people.name from people, students, students_courses, courses, disciplines 
										where students.id_student = students_courses.id_student and
										courses.id_course = students_courses.id_course and
										people.id_people = students.id_people and
										disciplines.id_course = courses.id_course union
select disciplines.name,courses.name, people.name from people,courses, disciplines,
										 officials,employee_courses,functions
										where people.id_people = officials.id_people and
										officials.id_employee = functions.id_employee and
										officials.id_employee = employee_courses.id_employee and
										employee_courses.id_course = courses.id_course and
	