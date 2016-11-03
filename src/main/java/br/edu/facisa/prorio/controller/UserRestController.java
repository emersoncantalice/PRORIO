package br.edu.facisa.prorio.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.facisa.prorio.dao.UserDAO;
import br.edu.facisa.prorio.model.User;

@RestController
public class UserRestController {

	@Autowired
	UserDAO userService;

	// ------------- Retrieve All Users-------------------- \\

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = null;
		try {
			users = userService.findAllUsers();
		} catch (SQLException e) {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		if (users == null || users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// ---------------- Retrieve Single User ----------------- \\

	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("username") String username) {
		System.out.println("Fetching User with id " + username);
		User user = null;
		try {
			user = userService.findByName(username);
		} catch (SQLException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		if (user == null) {
			System.out.println("User with username " + username + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// ------------------- Create a User------------------------ \\

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getUsername());

		try {
			if (userService.isUserExist(user.getUsername())) {
				System.out.println("A User with name " + user.getUsername() + " already exist");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			} else {
				userService.saveUser(user);
			}
		} catch (SQLException e1) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{username}").buildAndExpand(user.getUsername()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User --------------------------- \\

	@RequestMapping(value = "/user/{username}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user) {
		System.out.println("Updating User " + username);

		User currentUser = null;
		try {
			currentUser = userService.findByName(username);
		} catch (SQLException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}

		if (currentUser == null) {
			System.out.println("User with username " + username + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setUsername(user.getUsername());
		currentUser.setUsername(user.getPassword());
		currentUser.setEmail(user.getEmail());
		currentUser.setEnabled(user.getEnabled());
		currentUser.setUserProfiles(user.getUserProfiles());
		
		

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User ------------------------- \\	
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("username") String username) {
		System.out.println("Fetching & Deleting User with username " + username);

		User user = null;
		try {
			user = userService.findByName(username);
			userService.deleteUserByUsername(username);
		} catch (SQLException e1) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		if (user == null) {
			System.out.println("Unable to delete. User with username " + username + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users ---------------------- \\

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deleting All Users");

		try {
			userService.deleteAllUsers();
		} catch (SQLException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}