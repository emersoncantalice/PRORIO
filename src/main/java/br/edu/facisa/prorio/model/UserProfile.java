package br.edu.facisa.prorio.model;

import java.io.Serializable;

public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String type;

	public UserProfile(long id, String type) {
		this.id = id;
		this.type = type;
	}
	
	public UserProfile(long l) {
		this.id = l;
		this.type = UserProfileType.USER.getUserProfileType();
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", type=" + type + "]";
	}

}
