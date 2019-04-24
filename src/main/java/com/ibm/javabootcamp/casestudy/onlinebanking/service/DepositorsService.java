package com.ibm.javabootcamp.casestudy.onlinebanking.service;

import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Depositors;

public interface DepositorsService {
	
	public void add(Depositors depositor);

	public List<Depositors> findAll();

	public Depositors find(Long id);

	public List<Depositors> findByName(String dep_fname, String dep_lname);
}
