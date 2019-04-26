package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Payments;


public interface PaymentsDao {
	
	public List<Payments> findAll();

	public Payments findPayments(Long id);

	public void addPayment(Payments payment);
	
}
