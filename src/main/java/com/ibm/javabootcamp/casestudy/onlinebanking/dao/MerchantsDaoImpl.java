package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Depositors;
import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Merchants;

public class MerchantsDaoImpl extends HsqlDbConnection implements MerchantsDao{

	private static MerchantsDaoImpl INSTANCE;


	static public MerchantsDaoImpl getinstance() {
		MerchantsDaoImpl instance;
		if (INSTANCE != null) {

			instance = INSTANCE;

		} else {
			instance = new MerchantsDaoImpl();
			INSTANCE = instance;
		}

		return instance;

	}

	public MerchantsDaoImpl() {
		init();
	}
	
	
	
	@Override
	public List<Merchants> findAll() {
		
		return findByName(null);
	}

	@Override
	public Merchants findMerchant(Long id) {
		Merchants merchant = null;
		
		if (id != null) {
			String sql = "SELECT * FROM merchants where merch_id = ?";
			
			try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, id.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					merchant = new Merchants(Long.valueOf(results.getInt("merch_id")), results.getString("merch_name"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		return merchant;
	}

	@Override
	public void addMerchant(Merchants merchants) {
		
		String inSql = "INSERT INTO Merchants (merch_name) VALUES (?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(inSql)) {

			ps.setString(1, merchants.getMerch_name());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Merchants> findByName(String merch_name) {
		
		List<Merchants> merchant = new ArrayList<>();

		String sql = "SELECT * FROM merchants WHERE merch_name LIKE ?";
		
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setString(1, createSearchValue(merch_name));

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Merchants merch = new Merchants(Long.valueOf(results.getInt("merch_id")), results.getString("merch_name"));
				merchant.add(merch);
			}

		} catch (Exception e) {
		
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return merchant;
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
