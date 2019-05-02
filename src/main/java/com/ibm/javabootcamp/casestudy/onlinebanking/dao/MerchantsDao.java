package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Merchants;

public interface MerchantsDao {
	
	public List<Merchants> findAll();

	public Merchants findMerchant(Long id);
	
	public List<Merchants> findByName(String merch_name);

	public void addMerchant(Merchants merchants);
	
}
