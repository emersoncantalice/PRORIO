package br.edu.facisa.prorio.dao;

import java.util.List;

import br.edu.facisa.prorio.model.User;

public interface UserDAO {

	User findById(long id);

	User findByName(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	public boolean isUserExist(User user);

}
