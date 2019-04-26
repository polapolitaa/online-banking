package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;

public class AccountsJdbcConnect extends HsqlDbConnection implements AccountsDao {
	
	private static AccountsJdbcConnect INSTANCE;
	
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
		
		init();
	}


	@Override
	public void addAccount(Accounts accounts) {
		String inSql = "INSERT INTO ACCOUNTS (acct_no, acct_shortName, acct_type, curr_balance) VALUES (?, ?, ?, ?)";
		
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(inSql)) {
			
			ps.setBigDecimal(1, accounts.getAcct_no());
			ps.setString(2, accounts.getAcct_shortname());
			ps.setString(3, accounts.getAcct_type());
			ps.setBigDecimal(4, accounts.getCurr_balance());
			ps.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	@Override
	public List<Accounts> findAll() {
		
		return findByName(new BigDecimal(0), null);
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
					account = new Accounts(results.getBigDecimal("acct_no"), results.getString("acct_shortName"),
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
	public List<Accounts> findByName(BigDecimal acct_no, String acct_shortName) {
		List<Accounts> account = new ArrayList<>();

		String sql = "SELECT * FROM accounts WHERE acct_no LIKE ? OR acct_shortName LIKE ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setBigDecimal(1, acct_no);
			ps.setString(2, createSearchValue(acct_shortName));

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Accounts acct = new Accounts(results.getBigDecimal("acct_no"), results.getString("acct_shortName"),
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

	@Override
	public void update(Accounts account) {
		
		String updateSql = "UPDATE ACCOUNTS SET acct_shortName = ? WHERE acct_no = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setString(1, account.getAcct_shortname());
			ps.setBigDecimal(2, account.getAcct_no());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
