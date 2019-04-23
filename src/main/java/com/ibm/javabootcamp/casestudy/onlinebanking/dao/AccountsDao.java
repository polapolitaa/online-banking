package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;


public interface AccountsDao {
	
	public List<Accounts> findAll();

	public Accounts findAccount(Long id);

	public List<Accounts> findByName(String acct_shortName, String acct_type, BigDecimal curr_balance);

	public void addAccount(Accounts accounts);
}
