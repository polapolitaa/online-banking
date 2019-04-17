package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Depositors;

public class JdbcConnection implements DepositorDao{

	private static JdbcConnection INSTANCE;

	private JDBCDataSource dataSource;

	static public JdbcConnection getinstance() {
		JdbcConnection instance;
		if (INSTANCE != null) {

			instance = INSTANCE;

		} else {
			instance = new JdbcConnection();
			INSTANCE = instance;
		}

		return instance;

	}

	// db connection
	private JdbcConnection() {

		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:USER");
		dataSource.setUser("user");
		dataSource.setPassword("pass");

		System.out.println("Connection Successful");

		createTables();
		insertInitDepositors();
	}

	// creating tables in db
	private void createTables() {

		depTable();
		acctTable();
		merchTable();
		paymentTable();
		transacTable();

	}

	private void depTable() {

		String dep_table = "CREATE TABLE DEPOSITORS" + "(dep_id INTEGER IDENTITY PRIMARY KEY, "
				+ "dep_fname VARCHAR(255), " + "dep_mname VARCHAR(255), " + "dep_lname VARCHAR(255), "
				+ "dep_address VARCHAR(255), " + "dep_contact INTEGER)";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(dep_table);
			System.out.println("Table depositors Created");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private void acctTable() {

		String acct_Table = "CREATE TABLE ACCOUNTS" + "(acct_no INTEGER IDENTITY PRIMARY KEY, "
				+ "acct_shortName VARCHAR(255), " + "acct_Type VARCHAR(255), " + "curr_bal BIGDECIMAL)";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(acct_Table);
			System.out.println("Accounts Table Created");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private void merchTable() {

		String merch_table = "CREATE TABLE MERCHANTS" + "(merch_id INTEGER IDENTITY PRIMARY KEY, "
				+ "merch_name VARCHAR(255))";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(merch_table);
			System.out.println("Merchants Table Created");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void paymentTable() {

		String pymnt_table = "CREATE TABLE PAYMENTS" + "(pymnt_id INTEGER IDENTITY PRIMARY KEY, "
				+ "reference_no INTEGER, " + "pymnt_amount BIGDECIMAL, " + "pymnt_date DATE)";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(pymnt_table);
			System.out.println("Payments Table Created");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void transacTable() {

		String transact_table = "CREATE TABLE TRANSACTIONS" + "(transact_id INTEGER IDENTITY PRIMARY KEY, "
				+ "transact_date DATE, " + "transact_desc VARCHAR(255))";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(transact_table);
			System.out.println("Payments Table Created");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitDepositors() {
		
	}
	
public void add(Depositors depositor) {
		
		String inSql = "INSERT INTO DEPOSITORS (dep_fname, dep_lname) VALUES (?, ?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(inSql)) {

			ps.setString(1, depositor.getDep_fname());
			ps.setString(2, depositor.getDep_lname());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
