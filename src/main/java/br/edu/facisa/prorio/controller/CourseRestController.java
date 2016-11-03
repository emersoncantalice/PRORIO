package br.edu.facisa.prorio.controller;
 
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.facisa.prorio.dao.CourseDAO;
import br.edu.facisa.prorio.model.Course;
import br.edu.facisa.prorio.model.Discipline;
 
@RestController
public class CourseRestController {
 
    @Autowired
    CourseDAO courseService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve all courses--------------------------------------------------------
     
    @RequestMapping(value = "/course/", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> listAll() {
        List<Course> courses = null;
		try {
			courses = courseService.findAll();
		} catch (SQLException e) {
			return new ResponseEntity<List<Course>>(HttpStatus.BAD_REQUEST);
		}
        if(courses == null || courses.isEmpty()){
            return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }
    
  //-------------------Retrieve all disciplines of course--------------------------------------------------------
    
    @RequestMapping(value = "/course/disciplines/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Discipline>> getCursosDisciplinas(@PathVariable("id") long id) {
        System.out.println("Fetching disciplines of course " + id);
        List<Discipline> disciplines;
		try {
			disciplines = courseService.findDisciplinesOfCouseById(id);
		} catch (SQLException e) {
			return new ResponseEntity<List<Discipline>>(HttpStatus.BAD_REQUEST);
		}
        if (disciplines.size() == 0) {
            return new ResponseEntity<List<Discipline>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Discipline>>(disciplines, HttpStatus.OK);
    }
 
}