package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Depositors;

public interface DepositorDao {

	public List<Depositors> findAll();

	public Depositors find(Long id);

	public List<Depositors> findByName(String dep_fname, String dep_lname);

	public void add(Depositors depositor);

}
