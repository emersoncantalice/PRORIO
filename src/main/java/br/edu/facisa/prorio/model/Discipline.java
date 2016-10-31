package br.edu.facisa.prorio.model;

public class Discipline {

	private long id;

	private String nome;

	public Discipline() {
		id = 0;
	}

	public Discipline(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Discipline))
			return false;
		Discipline other = (Discipline) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Discipline [id=" + id + ", nome=" + nome + "]";
	}

}
