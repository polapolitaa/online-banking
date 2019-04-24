package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;


public interface AccountsDao {
	
	public List<Accounts> findAll();

	public Accounts findAccount(Long id);

	public List<Accounts> findByName(BigDecimal acct_no, String acct_shortName);

	public void addAccount(Accounts accounts);
	
	public void update(Accounts account);
	
	
}
