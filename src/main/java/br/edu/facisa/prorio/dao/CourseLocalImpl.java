package br.edu.facisa.prorio.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.edu.facisa.prorio.model.Course;
import br.edu.facisa.prorio.model.Discipline;

@Service("courseService")
public class CourseLocalImpl implements CourseDAO {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Course> courses;
	private static List<Discipline> diciplines;

	static {
		diciplines = populateDisciplines();
		courses = populate();
	}

	public List<Course> findAll() {
		return courses;
	}

	public Course findById(long id) {
		for (Course course : courses) {
			if (course.getId() == id) {
				return course;
			}
		}
		return null;
	}

	public Course findByName(String name) {
		for (Course course : courses) {
			if (course.getNome().equalsIgnoreCase(name)) {
				return course;
			}
		}
		return null;
	}

	public void save(Course course) {
		course.setId(counter.incrementAndGet());
		courses.add(course);
	}

	public void update(Course course) {
		int index = courses.indexOf(course);
		courses.set(index, course);
	}

	public void deleteById(long id) {

		for (Iterator<Course> iterator = courses.iterator(); iterator.hasNext();) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isExist(Course course) {
		return findByName(course.getNome()) != null;
	}

	public void deleteAll() {
		courses.clear();
	}

	private static List<Course> populate() {
		List<Course> course = new ArrayList<Course>();
		List<Discipline> disciplinesOfMed = new ArrayList<Discipline>();
		List<Discipline> disciplinesOfSI = new ArrayList<Discipline>();
		List<Discipline> disciplinesOfArq = new ArrayList<Discipline>();
		disciplinesOfSI.add(diciplines.get(0));
		disciplinesOfSI.add(diciplines.get(1));
		disciplinesOfMed.add(diciplines.get(4));
		disciplinesOfMed.add(diciplines.get(5));
		disciplinesOfArq.add(diciplines.get(2));
		disciplinesOfArq.add(diciplines.get(3));
		disciplinesOfArq.add(diciplines.get(2));

		course.add(new Course(counter.incrementAndGet(), "Sistemas de Informação", disciplinesOfSI));
		course.add(new Course(counter.incrementAndGet(), "Medicina", disciplinesOfMed));
		course.add(new Course(counter.incrementAndGet(), "Arquitetura", disciplinesOfArq));
		return course;
	}

	private static List<Discipline> populateDisciplines() {
		List<Discipline> dis = new ArrayList<Discipline>();
		dis.add(new Discipline(counter.incrementAndGet(), "P1", 1));
		dis.add(new Discipline(counter.incrementAndGet(), "P2", 3));
		dis.add(new Discipline(counter.incrementAndGet(), "Geometria Discritiva", 5));
		dis.add(new Discipline(counter.incrementAndGet(), "Desenho Basico", 7));
		dis.add(new Discipline(counter.incrementAndGet(), "Cardologia", 8));
		dis.add(new Discipline(counter.incrementAndGet(), "Biologia", 6));
		return dis;
	}

	@Override
	public List<Discipline> findDisciplinesOfCouseById(long id) {
		List<Discipline> disciplines = new ArrayList<Discipline>();
		for (Course curso : courses) {
			if (curso.getId() == id) {
				disciplines = curso.getDisciplines();
			}
		}
		return disciplines;
	}

}
