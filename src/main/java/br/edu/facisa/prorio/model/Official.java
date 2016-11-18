package br.edu.facisa.prorio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Official extends People {
	
	private int idOfficial;
	private String recordNumber;
	private List<Function> funcao = new ArrayList<Function>();
	private Availability availability; 
	
	public Official(String name, Date birthDate, String cpf, String recordNumber) {
		super(name, birthDate, cpf);
		this.recordNumber = recordNumber;
	}

	public String getRecord_number() {
		return recordNumber;
	}

	public void setRecord_number(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public List<Function> getFuncao() {
		return funcao;
	}

	public void setFuncao(List<Function> funcao) {
		this.funcao = funcao;
	}

	public int getIdOfficial() {
		return idOfficial;
	}

	public void setIdOfficial(int idOfficial) {
		this.idOfficial = idOfficial;
	}
	
}
