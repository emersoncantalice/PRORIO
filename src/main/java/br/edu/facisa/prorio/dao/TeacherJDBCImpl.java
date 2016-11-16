package br.edu.facisa.prorio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.facisa.prorio.model.Official;

public class TeacherJDBCImpl {
	
	@Autowired
	public DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void save(Official official) throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			String insert_sql_people = "INSERT INTO people(name, birth_date,cpf) VALUES (?, ?, ?);";
			String insert_sql_official = "INSERT INTO officials(id_people, record_number) VALUES (?, ?);";
			PreparedStatement pst;
			pst = conn.prepareStatement(insert_sql_people);
			pst.setString(1, official.getName());
			pst.setDate(2, (Date) official.getBirthDate());
			pst.setString(3, official.getCpf());
			pst.executeUpdate();
			
			pst = conn.prepareStatement(insert_sql_official);
			pst.setInt(1, official.getId());
			pst.setString(2, official.getRecord_number());

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
	

}
