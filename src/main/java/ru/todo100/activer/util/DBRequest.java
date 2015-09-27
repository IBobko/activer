package ru.todo100.activer.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings(value={"unused"})
public class DBRequest {
	private SessionFactory sessionFactory;
	private Connection connection;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) sessionFactory;
		ConnectionProvider cp = sfi.getConnectionProvider();
		try {
			this.connection = cp.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet query(String s) throws SQLException{
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) sessionFactory;
		ConnectionProvider cp = sfi.getConnectionProvider();
		Connection conn = null;
		ResultSet rs = null;
			conn = cp.getConnection();
			conn.createStatement().execute(s);
			

			
			System.out.println(s);
			System.out.println("----------");

		return rs;
	}
	
	public ArrayList<String> getSqlFromInputStream(InputStream is) throws IOException {
		ArrayList<String> asql = new ArrayList<String>(); 

			String line = "";
			int symbol = 0;
			boolean quote = false;
			while ((symbol = is.read()) != -1) {
				if (symbol == '\'') {
					quote = !quote;
				}
				if (symbol == ';' && !quote) {
					asql.add(line);
					line = "";
					continue;
				}
				line += (char)symbol;
			}

		return asql;
	}
}