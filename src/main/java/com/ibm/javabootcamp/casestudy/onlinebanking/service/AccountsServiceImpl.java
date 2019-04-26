package com.ibm.javabootcamp.casestudy.onlinebanking.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.javabootcamp.casestudy.onlinebanking.dao.AccountsJdbcConnect;
import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;

public class AccountsServiceImpl implements AccountsService{
	
	AccountsJdbcConnect accountsDao;
	
	public AccountsServiceImpl() {
		this.accountsDao = AccountsJdbcConnect.getinstance();
	}

	@Override
	public void addAccount(Accounts account) {
		if(validate(account)) {
			accountsDao.addAccount(account);
		}else {
			System.out.println("Fields is empty");
		}
		
	}
	
	private boolean validate(Accounts account) {
		return !StringUtils.isAnyBlank(account.getAcct_shortname(), account.getAcct_type());
	}

	@Override
	public List<Accounts> findAll() {
		
		return accountsDao.findAll();
	}

	@Override
	public Accounts find(Long id) {
		
		return accountsDao.findAccount(id);
	}

	@Override
	public List<Accounts> findByName(BigDecimal acct_no, String acc_shortName) {
		
		return accountsDao.findByName(acct_no, acc_shortName);
	}

	@Override
	public void update(Accounts account) {
	
				accountsDao.update(account);
		
	}
	

}
