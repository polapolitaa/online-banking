package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;
import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Depositors;
import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Merchants;

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
		String inSql = "INSERT INTO ACCOUNTS2 (acct_no, acct_shortName, acct_type, curr_balance, dep_id_fk ) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(inSql)) {

			ps.setBigDecimal(1, accounts.getAcct_no());
			ps.setString(2, accounts.getAcct_shortname());
			ps.setString(3, accounts.getAcct_type());
			ps.setBigDecimal(4, accounts.getCurr_balance());
			ps.setInt(5, 0);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Accounts> showAcctDetails() {

		List<Accounts> account = new ArrayList<>();

		String sql = "SELECT * FROM accounts2 where dep_id_fk = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, 0);
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

	@Override
	public Accounts findAccount(Long id) {

		Accounts account = null;

		if (id != null) {
			String sql = "SELECT * FROM accounts2 where acct_no = ?";

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
	public void update(Accounts account) {

		String updateSql = "UPDATE ACCOUNTS2 SET acct_shortName = ? WHERE acct_no = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setString(1, account.getAcct_shortname());
			ps.setBigDecimal(2, account.getAcct_no());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void payMerchant(Accounts account) {

		String sql = "UPDATE ACCOUNTS2 SET curr_balance = ? where acct_no = ?";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setBigDecimal(1, account.getCurr_balance());
			ps.setBigDecimal(2, account.getAcct_no());


		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void transferFundAdd(Accounts account, Long id) {

//		String updateSql = "UPDATE ACCOUNTS SET curr_balance = ? WHERE acct_no = ?";
//		String sql = "SELECT curr_balance FROM accounts where acct_no = ?";
//		
//		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql); PreparedStatement ps2 = conn.prepareStatement(sql)) {
//			
//			ps2.setInt(1, id.intValue());
//			ResultSet results = ps.executeQuery();
//			
//			BigDecimal sample1 = results.getBigDecimal("curr_balance");
//			BigDecimal sample2 = new BigDecimal(1000);
//			BigDecimal sample3 = sample1.subtract(sample2);
//			
//			System.out.println(sample3);
//			
//			
//			ps.setBigDecimal(1, account.getCurr_balance());
//			ps.setBigDecimal(2, account.getAcct_no());
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		

	}
}
