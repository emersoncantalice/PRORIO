package br.edu.facisa.prorio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.facisa.prorio.model.Course;
import br.edu.facisa.prorio.model.Discipline;

@Service("courseService")
public class CourseJDBCImpl implements CourseDAO {

	@Autowired
	public DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Course> findAll() throws SQLException {

		String sql = "SELECT * FROM COURSES";

		Connection conn = null;
		List<Course> courses = null;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst;
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			courses = new ArrayList<Course>();

			while (rs.next()) {
				courses.add(mapeamento(rs));
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
		return courses;
	
	}

	public Course findById(long id) throws SQLException {
		String sql = "SELECT * FROM COURSES WHERE ID = ?";

		Connection conn = null;
		Course course = null;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst;
			pst = conn.prepareStatement(sql);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			course = new Course();

			while (rs.next()) {
				course = (mapeamento(rs));
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
		return course;
	}

	public Course findByName(String name) throws SQLException {
		String sql = "SELECT * FROM COURSES WHERE ID = ?";

		Connection conn = null;
		Course course = null;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst;
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			course = new Course();

			while (rs.next()) {
				course = (mapeamento(rs));
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
		return course;
	}

	public void save(Course course) throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			String insert_sql_course = "INSERT INTO courses(name) VALUES (?);";
			String insert_sql_disciplines = "INSERT INTO disciplines(id_course, name, period, credits) VALUES (?, ?, ?, ?),";
			PreparedStatement pst;
			pst = conn.prepareStatement(insert_sql_course);
			pst.setString(1, course.getNome());
			pst.executeUpdate();

			for (Discipline up : course.getDisciplines()) {
				pst = conn.prepareStatement(insert_sql_disciplines);
				pst.setLong(1, course.getId());
				pst.setString(2, up.getNome());
				pst.setInt(3, up.getPeriod());
				pst.setInt(4, up.getCredits());
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

	public void update(Course course) {
		try {
			if (isExist(course)) {
				deleteById(course.getId());
				save(course);
			} else {
				throw new DAOException();
			}
		} catch (DAOException e) {
			throw new DAOException("Usuario não encontrado");
		} catch (Exception e) {
			throw new DAOException("Erro no banco de dados");
		}
	}

	public void deleteById(long id) throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			String delete_sql_disciplines = "delete from disciplines where id = ?";
			String delete_sql_courses = "delete from courses where id = ?";
			PreparedStatement pst;
			pst = conn.prepareStatement(delete_sql_disciplines);
			pst.setLong(1, id);
			pst.executeUpdate();

			pst = conn.prepareStatement(delete_sql_courses);
			pst.setLong(1, id);
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

	public boolean isExist(Course course) throws SQLException {
		return findByName(course.getNome()) != null;
	}

	public void deleteAll() throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			String delete_sql_disciplines = "delete from disciplines";
			String delete_sql_courses = "delete from courses";
			PreparedStatement pst;
			pst = conn.prepareStatement(delete_sql_disciplines);
			pst.executeUpdate();

			pst = conn.prepareStatement(delete_sql_courses);
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
	public List<Discipline> findDisciplinesOfCouseById(long idCourse) throws SQLException {
		String sql = "SELECT * FROM DISCIPLINES WHERE ID_COURSE = ?";

		Connection conn = null;
		List<Discipline> disciplines = null;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst;
			pst = conn.prepareStatement(sql);
			pst.setLong(1, idCourse);
			ResultSet rs = pst.executeQuery();
			disciplines = new ArrayList<Discipline>();

			while (rs.next()) {
				disciplines.add(mapeamentoDisciplines(rs));
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
		return disciplines;
	}
	
	private static Course mapeamento(ResultSet rs) throws SQLException {
		Course course = new Course();
		course.setId(rs.getLong("id_course"));
		course.setNome(rs.getString("name"));
		return course;
	}
	
	private static Discipline mapeamentoDisciplines(ResultSet rs) throws SQLException {
		Discipline discipline = new Discipline();
		discipline.setId(rs.getLong("id_discipline"));
		discipline.setNome(rs.getString("name"));
		discipline.setIdCourse(rs.getLong("id_course"));
		discipline.setPeriod(rs.getInt("period"));
		discipline.setCredits(rs.getInt("credits"));
		return discipline;
	}

}
