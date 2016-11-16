package br.edu.facisa.prorio.model;

public class Availability {
	
	private int idAvailability;
	private String scheduleOnene;
	private String scheduleTwo;
	private String scheduleThree;
	private String scheduleFour;
	private String scheduleFive;
	private String scheduleSiz;
	
	public Availability(String scheduleOnene, String scheduleTwo, String scheduleThree, String scheduleFour,
			String scheduleFive, String scheduleSiz) {
		this.scheduleOnene = scheduleOnene;
		this.scheduleTwo = scheduleTwo;
		this.scheduleThree = scheduleThree;
		this.scheduleFour = scheduleFour;
		this.scheduleFive = scheduleFive;
		this.scheduleSiz = scheduleSiz;
	}

	public String getScheduleOnene() {
		return scheduleOnene;
	}

	public void setScheduleOnene(String scheduleOnene) {
		this.scheduleOnene = scheduleOnene;
	}

	public String getScheduleTwo() {
		return scheduleTwo;
	}

	public void setScheduleTwo(String scheduleTwo) {
		this.scheduleTwo = scheduleTwo;
	}

	public String getScheduleThree() {
		return scheduleThree;
	}

	public void setScheduleThree(String scheduleThree) {
		this.scheduleThree = scheduleThree;
	}

	public String getScheduleFour() {
		return scheduleFour;
	}

	public void setScheduleFour(String scheduleFour) {
		this.scheduleFour = scheduleFour;
	}

	public String getScheduleFive() {
		return scheduleFive;
	}

	public void setScheduleFive(String scheduleFive) {
		this.scheduleFive = scheduleFive;
	}

	public String getScheduleSiz() {
		return scheduleSiz;
	}

	public void setScheduleSiz(String scheduleSiz) {
		this.scheduleSiz = scheduleSiz;
	}

	public int getIdAvailability() {
		return idAvailability;
	}

	public void setIdAvailability(int idAvailability) {
		this.idAvailability = idAvailability;
	}
	
}
