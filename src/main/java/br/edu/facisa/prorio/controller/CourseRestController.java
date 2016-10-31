package br.edu.facisa.prorio.controller;
 
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
        List<Course> courses = courseService.findAll();
        if(courses.isEmpty()){
            return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }
    
  //-------------------Retrieve all disciplines of course--------------------------------------------------------
    
    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Discipline>> getCursosDisciplinas(@PathVariable("id") long id) {
        System.out.println("Fetching disciplines with id of course " + id);
        List<Discipline> disciplines = courseService.findDisciplinesOfCouseById(id);
        if (disciplines.size() == 0) {
            return new ResponseEntity<List<Discipline>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Discipline>>(disciplines, HttpStatus.OK);
    }
 
}