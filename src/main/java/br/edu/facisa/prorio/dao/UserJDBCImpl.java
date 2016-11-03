package br.edu.facisa.prorio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.edu.facisa.prorio.model.User;
import br.edu.facisa.prorio.model.UserProfile;

@Component
@Service("userJdbcDAO")
public class UserJDBCImpl implements UserDAO {

	@Autowired
	public DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<User> findAllUsers() throws SQLException {
		String sql = "SELECT * FROM USERS";
		String sqlProfile = "SELECT * FROM USER_PROFILE";

		Connection conn = null;
		List<User> users = null;
		Set<UserProfile> authorities;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst;
			pst = conn.prepareStatement(sqlProfile);
			ResultSet rs = pst.executeQuery();
			authorities = new HashSet<UserProfile>();
			users = new ArrayList<User>();

			while (rs.next()) {
				authorities.add(mapeamentoProfile(rs));
			}

			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				users.add(mapeamento(rs, authorities));
			}

			conn.commit();
			rs.close();
			pst.close();

		} catch (SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return users;
	}

	public User findByName(String name) throws SQLException {
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		String sqlProfile = "SELECT * FROM USER_PROFILE WHERE USERNAME = ?";

		Connection conn = null;
		User user = null;
		Set<UserProfile> authorities;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst;
			pst = conn.prepareStatement(sqlProfile);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			authorities = new HashSet<UserProfile>();

			while (rs.next()) {
				authorities.add(mapeamentoProfile(rs));
			}

			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();

			while (rs.next()) {
				user = mapeamento(rs, authorities);
			}

			conn.commit();
			rs.close();
			pst.close();

		} catch (SQLException e) {
			conn.rollback();
			throw new DAOException("Erro no banco de dados!");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return user;
	}

	private UserProfile mapeamentoProfile(ResultSet rs) throws SQLException {
		UserProfile profile = new UserProfile();
		profile.setId(rs.getInt("id_profile"));
		profile.setType(rs.getString("role"));
		profile.setUsername(rs.getString("username"));
		return profile;
	}

	@Override
	public void saveUser(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			String insert_sql_user = "INSERT INTO users(username, password, enabled, email) VALUES (? , ?, ?, ?)";
			String insert_sql_profiles = "INSERT INTO user_profile(username,role) VALUES (?, ?)";
			PreparedStatement pst;
			pst = conn.prepareStatement(insert_sql_user);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getEnabled());
			pst.setString(4, user.getEmail());
			pst.executeUpdate();

			for (UserProfile up : user.getUserProfiles()) {
				pst = conn.prepareStatement(insert_sql_profiles);
				pst.setString(1, user.getUsername());
				pst.setString(2, up.getType());
				pst.executeUpdate();
			}

			conn.commit();
			pst.close();

		} catch (SQLException e) {
			conn.rollback();
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.", e);
			}
		}
	}

	@Override
	public void updateUser(User user) {
		try {
			if (isUserExist(user.getUsername())) {
				deleteUserByUsername(user.getUsername());
				saveUser(user);
			} else {
				throw new DAOException();
			}
		} catch (DAOException e) {
			throw new DAOException("Usuario não encontrado");
		} catch (Exception e) {
			throw new DAOException("Erro no banco de dados");
		}
	}

	@Override
	public void deleteUserByUsername(String username) throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			String delete_sql_profiles = "delete from user_profile where username = ?";
			String delete_sql_user = "delete from users where username = ?";
			PreparedStatement pst;
			pst = conn.prepareStatement(delete_sql_profiles);
			pst.setString(1, username);
			pst.executeUpdate();

			pst = conn.prepareStatement(delete_sql_user);
			pst.setString(1, username);
			pst.executeUpdate();

			conn.commit();
			pst.close();

		} catch (SQLException e) {
			conn.rollback();
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.", e);
			}
		}

	}

	@Override
	public void deleteAllUsers() throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			String delete_sql_profiles = "delete from user_profile";
			String delete_sql_user = "delete from users where username";
			PreparedStatement pst;
			pst = conn.prepareStatement(delete_sql_profiles);
			pst.executeUpdate();

			pst = conn.prepareStatement(delete_sql_user);
			pst.executeUpdate();

			conn.commit();
			pst.close();

		} catch (SQLException e) {
			conn.rollback();
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.", e);
			}
		}

	}

	@Override
	public boolean isUserExist(String username) throws SQLException {
		return findByName(username) != null;
	}

	private static User mapeamento(ResultSet rs, Set<UserProfile> authorities) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEnabled(rs.getInt("enabled"));
		user.setEmail(rs.getString("email"));

		Set<UserProfile> authoritiesUser = new HashSet<UserProfile>();
		for (UserProfile userProfile : authorities) {
			if (userProfile.getUsername().equals(user.getUsername())) {
				authoritiesUser.add(userProfile);
			}
		}

		user.setUserProfiles(authoritiesUser);
		return user;
	}

}
