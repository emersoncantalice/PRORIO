package br.edu.facisa.prorio.model;

import java.io.Serializable;

public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id_profile;

	private String type;

	private String username;

	public UserProfile() {
	}

	public UserProfile(long l) {
		this.id_profile = l;
		this.type = UserProfileType.USER.getUserProfileType();
	}

	public long getId() {
		return id_profile;
	}

	public void setId(Integer id) {
		this.id_profile = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id_profile + ", type=" + type + "]";
	}

}
