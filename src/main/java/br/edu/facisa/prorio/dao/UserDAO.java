package br.edu.facisa.prorio.dao;

import java.sql.SQLException;
import java.util.List;

import br.edu.facisa.prorio.model.User;

public interface UserDAO {

	User findByName(String name) throws SQLException;

	void saveUser(User user) throws SQLException;

	void updateUser(User user);

	void deleteUserByUsername(String username) throws SQLException;

	List<User> findAllUsers() throws SQLException;

	void deleteAllUsers() throws SQLException;

	public boolean isUserExist(String username) throws SQLException;

}
