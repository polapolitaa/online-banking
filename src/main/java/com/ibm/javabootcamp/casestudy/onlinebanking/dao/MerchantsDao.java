package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Merchants;

public interface MerchantsDao {
	
	public List<Merchants> findAll();

	public Merchants findAccount(Long id);

	public void addMerchant(Merchants merchants);
	
	public void deleteMerchant(Merchants mechants);
	
}
