package br.edu.facisa.prorio.dao;

import java.sql.SQLException;
import java.util.List;

import br.edu.facisa.prorio.model.Course;
import br.edu.facisa.prorio.model.Discipline;

public interface CourseDAO {
	
	Course findById(long id) throws SQLException;
	
	List<Discipline> findDisciplinesOfCouseById(long id) throws SQLException;
	
	Course findByName(String name) throws SQLException;
	
	void save(Course course) throws SQLException;
	
	void update(Course course);
	
	void deleteById(long id) throws SQLException;

	List<Course> findAll() throws SQLException; 
	
	void deleteAll() throws SQLException;
	
	public boolean isExist(Course course) throws SQLException;
	
}
