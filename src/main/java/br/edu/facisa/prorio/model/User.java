package br.edu.facisa.prorio.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class User {

	private long id;

	private String username;

	private String password;

	private String address;

	private String email;
	
	private static final AtomicLong counter = new AtomicLong();

	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	public User() {
		id = 0;
	}

	public User(long id, String username, String password, String address, String email) {
		this.id = id;
		this.username = username;
		this.address = address;
		this.email = email;
		this.userProfiles.add(new UserProfile(counter.incrementAndGet()));
	}
	
	

	public User(long id, String username, String password, String address, String email,
			Set<UserProfile> userProfiles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.address = address;
		this.email = email;
		this.userProfiles = userProfiles;
	}

	public long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", address=" + address + ", email=" + email + "]";
	}

}
