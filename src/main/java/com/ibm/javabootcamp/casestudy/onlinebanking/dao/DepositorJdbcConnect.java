package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Depositors;

public class DepositorJdbcConnect implements DepositorDao {
	
	private static DepositorJdbcConnect INSTANCE;

	private JDBCDataSource dataSource;

	static public DepositorJdbcConnect getinstance() {
		DepositorJdbcConnect instance;
		if (INSTANCE != null) {

			instance = INSTANCE;

		} else {
			instance = new DepositorJdbcConnect();
			INSTANCE = instance;
		}

		return instance;

	}

	// db connection
	private DepositorJdbcConnect() {

		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:USER");
		dataSource.setUser("username");
		dataSource.setPassword("password");

		createTables();
		insertInitDepositors();
	}

	// creating depositor table in db
	private void createTables() {

		depTable();

	}

	private void depTable() {

		String dep_table = "CREATE TABLE DEPOSITORS" + "(dep_id INTEGER IDENTITY PRIMARY KEY, "
				+ "dep_fname VARCHAR(255), " + "dep_mname VARCHAR(255), " + "dep_lname VARCHAR(255), "
				+ "dep_address VARCHAR(255), " + "dep_contact BIGINT)";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(dep_table);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	
	//initializing depositors
	private void insertInitDepositors() {

		add(new Depositors("Paula", "Dichoso", "test", "teeeeest", 9560598369L));
		add(new Depositors("Dennise", "Ramilla" , "test", "teest!", 9565463377L));

	}

	public void add(Depositors depositor) {

		String inSql = "INSERT INTO DEPOSITORS (dep_fname, dep_lname, dep_mname, dep_address, dep_contact) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(inSql)) {

			ps.setString(1, depositor.getDep_fname());
			ps.setString(2, depositor.getDep_lname());
			ps.setString(3, depositor.getDep_mname());
			ps.setString(4, depositor.getAddress());
			ps.setLong(5, depositor.getContactNo());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Depositors> findAll() {

		return findByName(null, null, null, null, 0);

	}

	@Override
	public Depositors find(Long id) {

		Depositors depositor = null;

		if (id != null) {
			String sql = "SELECT * FROM depositors where dep_id = ?";

			try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, id.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					depositor = new Depositors(Long.valueOf(results.getInt("dep_id")), results.getString("dep_fname"),
							results.getString("dep_lname"), results.getString("dep_mname"), results.getString("dep_address"), 
							Long.valueOf(results.getLong("dep_contact")));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return depositor;
	}

	@Override
	public List<Depositors> findByName(String dep_fname, String dep_lname, String dep_mname, String address, long contactNo) {

		List<Depositors> depositor = new ArrayList<>();

		String sql = "SELECT * FROM depositors WHERE dep_fname LIKE ? AND dep_lname LIKE ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(dep_fname));
			ps.setString(2, createSearchValue(dep_lname));

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Depositors dep = new Depositors(Long.valueOf(results.getInt("dep_id")), results.getString("dep_fname"),
						results.getString("dep_lname"), results.getString("dep_mname"), results.getString("dep_address"), 
						Long.valueOf(results.getLong("dep_contact")));
				depositor.add(dep);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return depositor;
	}

	private String createSearchValue(String string) {

		String value;

		if (StringUtils.isBlank(string)) {
			value = "%";
		} else {
			value = string;
		}

		return value;
	}
}
