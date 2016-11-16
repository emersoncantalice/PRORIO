package br.edu.facisa.prorio.model;

import java.util.Date;

public class People {
	
	private int idPeople;
	private String name;
	private Date birthDate;
	private String cpf;
	
	public People(String name, Date birthDate, String cpf) {
		this.name = name;
		this.birthDate = birthDate;
		this.cpf = cpf;
	}

	public int getId() {
		return idPeople;
	}

	public void setId(int id) {
		this.idPeople = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
