package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Depositors;

public class DepositorJdbcConnect extends HsqlDbConnection implements DepositorDao {
	
	private static DepositorJdbcConnect INSTANCE;

	
	

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
		
		init();
		
	}
	
//	private DepositorJdbcConnect() {
//
//		dataSource = new JDBCDataSource();
//		dataSource.setDatabase("jdbc:hsqldb:hsql://localhost/onlinebanking");
//		dataSource.setUser("SA");
//		dataSource.setPassword("");
//		
//	}

	//ADDING DEPOSITORS
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

	//LIST ALL DEPOSITOR INFO
	@Override
	public List<Depositors> findAll() {
		
		
		return findByName(null, null);

	}
	
	//FIND DEPOSITOR ID
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
	
	
	//FIND DEPOSITOR BY FIRST NAME AND LAST NAME
	@Override
	public List<Depositors> findByName(String dep_fname, String dep_lname) {

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

		} catch (Exception e) {
		
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
