package br.edu.facisa.prorio.model;

public class Discipline {

	private long id;

	private String nome;

	private int period;

	private int credits;

	private long idCourse;

	public Discipline() {
		id = 0;
	}

	public Discipline(long id, String nome, int credits) {
		super();
		this.id = id;
		this.nome = nome;
		this.credits = credits;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
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

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(long idCourse) {
		this.idCourse = idCourse;
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
