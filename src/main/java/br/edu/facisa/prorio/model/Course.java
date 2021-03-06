package br.edu.facisa.prorio.model;

import java.util.List;

public class Course {

	private long id;

	private String name;

	private List<Discipline> disciplines;

	public Course() {
	}

	public Course(long id, String nome) {
		this.id = id;
		this.name = nome;
	}

	public Course(long id, String nome, List<Discipline> disciplines) {
		this.id = id;
		this.name = nome;
		this.disciplines = disciplines;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Course))
			return false;
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", nome=" + name + "]";
	}

}
