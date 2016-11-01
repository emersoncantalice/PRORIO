package br.edu.facisa.prorio.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.edu.facisa.prorio.model.User;
import br.edu.facisa.prorio.model.UserProfile;
import br.edu.facisa.prorio.model.UserProfileType;

@Component
@Service("userDAO")
public class UserLocalImpl implements UserDAO {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}

	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User findByName(String name) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername()) != null;
	}

	public void deleteAllUsers() {
		users.clear();
	}

	private static List<User> populateDummyUsers() {
		Set<UserProfile> userProfiles = new HashSet<UserProfile>();
		UserProfile profile = new UserProfile(counter.incrementAndGet(), UserProfileType.ADMIN.getUserProfileType());
		userProfiles.add(profile);
		List<User> users = new ArrayList<User>();
		
		users.add(new User(counter.incrementAndGet(), "maoliveira", "12345", "Campina Grande",
				"maoliveira@gmail.com"));
		users.add(new User(counter.incrementAndGet(), "escantalice", "12345", "Santos", "escantalice@gmail.com", userProfiles));
		users.add(new User(counter.incrementAndGet(), "ebrito", "12345", "Rondonia", "ebrito@gmail.com"));
		return users;
	}

}
