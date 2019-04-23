package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;

public class AccountsJdbcConnect implements AccountsDao {
	
	private static AccountsJdbcConnect INSTANCE;
	
	private JDBCDataSource dataSource;
	
	static public AccountsJdbcConnect getinstance() {
		AccountsJdbcConnect instance;
		if (INSTANCE != null) {

			instance = INSTANCE;

		} else {
			instance = new AccountsJdbcConnect();
			INSTANCE = instance;
		}
		
		return instance;
	}
	
	private AccountsJdbcConnect() {
		
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:USER");
		dataSource.setUser("username");
		dataSource.setPassword("password");

		createAcctTbl();
		insertInitAccounts();
	}
	
	
	private void createAcctTbl() {
		String acct_Table = "CREATE TABLE ACCOUNTS" + "(acct_no INTEGER IDENTITY PRIMARY KEY, "
				+ "acct_shortName VARCHAR(255), " + "acct_Type VARCHAR(255), " + "curr_balance DECIMAL)";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(acct_Table);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private void insertInitAccounts() {
		addAccount(new Accounts("BILLS","Savings", new BigDecimal(10000)));
	}

	@Override
	public void addAccount(Accounts accounts) {
		String inSql = "INSERT INTO ACCOUNTS (acct_shortName, acct_type, curr_balance) VALUES (?, ?, ?)";
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(inSql)) {

			ps.setString(1, accounts.getAcct_shortname());
			ps.setString(2, accounts.getAcct_type());
			ps.setBigDecimal(3, accounts.getCurr_balance());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public List<Accounts> findAll() {
		
		return findByName(null,null, new BigDecimal(0));
	}

	@Override
	public Accounts findAccount(Long id) {
		
		Accounts account = null;

		if (id != null) {
			String sql = "SELECT * FROM accounts where acct_no = ?";

			try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, id.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					account = new Accounts(Long.valueOf(results.getInt("acct_no")), results.getString("acct_shortName"),
							results.getString("acct_type"), results.getBigDecimal("curr_balance"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return account;
	}

	@Override
	public List<Accounts> findByName(String acct_shortName, String acct_type, BigDecimal curr_balance) {
		List<Accounts> account = new ArrayList<>();

		String sql = "SELECT * FROM accounts WHERE acct_shortName LIKE ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(acct_shortName));

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Accounts acct = new Accounts(Long.valueOf(results.getInt("acct_no")), results.getString("acct_shortName"),
						results.getString("acct_type"), results.getBigDecimal("curr_balance"));
				account.add(acct);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return account;
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
