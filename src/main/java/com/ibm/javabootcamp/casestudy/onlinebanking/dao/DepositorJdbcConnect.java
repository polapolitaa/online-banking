package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
	public List<Depositors> showDetails() {
		
		List<Depositors> depositor = new ArrayList<>();

		String sql = "SELECT * FROM depositors WHERE dep_id = ?";
		
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, 0);

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
	
	//FIND DEPOSITOR ID
	@Override
	public Depositors find(Long id) {

		Depositors depositor = null;
		
		System.out.println(id);

		if (id != 0) {
			String sql = "SELECT * FROM depositors where dep_id = ?";

			try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, id.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					depositor = new Depositors(Long.valueOf(results.getInt("dep_id")), results.getString("dep_fname"),
							results.getString("dep_lname"), results.getString("dep_mname"), results.getString("dep_address"), 
							Long.valueOf(results.getLong("dep_contact")));
					System.out.println("DEPOSITOR");
					System.out.println(depositor.getDep_id());
				}
				
				

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return depositor;
	}
}
