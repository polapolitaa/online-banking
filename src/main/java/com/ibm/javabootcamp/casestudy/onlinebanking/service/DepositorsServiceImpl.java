package com.ibm.javabootcamp.casestudy.onlinebanking.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.javabootcamp.casestudy.onlinebanking.dao.DepositorJdbcConnect;
import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Depositors;

public class DepositorsServiceImpl implements DepositorsService {
	DepositorJdbcConnect depositorDao;

	public DepositorsServiceImpl() {
		this.depositorDao = DepositorJdbcConnect.getinstance();
	}

	@Override
	public void add(Depositors depositor) {
		if(validate(depositor)) {
			depositorDao.add(depositor);
		}

	}
	
	private boolean validate(Depositors depositor) {
		return !StringUtils.isAnyBlank(depositor.getDep_fname(), depositor.getDep_lname(), depositor.getDep_mname(), depositor.getAddress());
	}

	@Override
	public List<Depositors> showDetails() {

		return depositorDao.showDetails();

	}

	@Override
	public Depositors find(Long id) {

		return depositorDao.find(id);
	}
//
//	@Override
//	public List<Depositors> findByName(String dep_fname, String dep_lname) {
//
//		return depositorDao.findByName(dep_fname, dep_lname);
//	}
}
