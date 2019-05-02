package com.ibm.javabootcamp.casestudy.onlinebanking.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.javabootcamp.casestudy.onlinebanking.dao.MerchantsDaoImpl;
import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Merchants;

public class MerchantsServiceImpl implements MerchantsService{
	
	MerchantsDaoImpl merchantDao;
	
	public MerchantsServiceImpl() {
		this.merchantDao = MerchantsDaoImpl.getinstance();
	}

	@Override
	public List<Merchants> findAll() {
		
		return merchantDao.findAll();
	}

	@Override
	public Merchants findMerchant(Long id) {
		return merchantDao.findMerchant(id);
	}

	@Override
	public List<Merchants> findByName(String merch_name) {
		
		return merchantDao.findByName(merch_name);
	}

	@Override
	public void addMerchant(Merchants merchant) {
		if(validate(merchant)) {
			merchantDao.addMerchant(merchant);
			
		}
		
	}
	
	private boolean validate(Merchants merchant) {
		return !StringUtils.isAnyBlank(merchant.getMerch_name());
	}

}
