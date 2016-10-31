package br.edu.facisa.prorio.dao;

import java.util.List;

import br.edu.facisa.prorio.model.Course;
import br.edu.facisa.prorio.model.Discipline;

public interface CourseDAO {
	
	Course findById(long id);
	
	List<Discipline> findDisciplinesOfCouseById(long id);
	
	Course findByName(String name);
	
	void save(Course course);
	
	void update(Course course);
	
	void deleteById(long id);

	List<Course> findAll(); 
	
	void deleteAll();
	
	public boolean isExist(Course course);
	
}
