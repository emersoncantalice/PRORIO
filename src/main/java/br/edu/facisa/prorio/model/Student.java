package br.edu.facisa.prorio.model;

import java.util.Date;

public class Student extends People{
	
	private int id;
	private String registration;
	
	public Student(String name, Date birthDate, String cpf, String registration) {
		super(name, birthDate, cpf);
		this.registration = registration;
	}
	
}
