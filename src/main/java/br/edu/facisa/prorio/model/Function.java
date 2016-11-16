package br.edu.facisa.prorio.model;

public class Function {
	
	private int idFunction;
	private String function;
	
	public Function(String function) {
		this.function = function;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public int getIdFunction() {
		return idFunction;
	}

	public void setIdFunction(int idFunction) {
		this.idFunction = idFunction;
	}
	
}
