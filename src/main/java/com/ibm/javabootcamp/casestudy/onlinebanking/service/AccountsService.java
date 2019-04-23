package com.ibm.javabootcamp.casestudy.onlinebanking.service;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;


public interface AccountsService {
	
	public void addAccount(Accounts account);

	public List<Accounts> findAll();

	public Accounts find(Long id);

	public List<Accounts> findByName(String acc_shortName, String acct_type, BigDecimal curr_balance);

}
