package com.ibm.javabootcamp.casestudy.onlinebanking.restcontroller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Depositors;
import com.ibm.javabootcamp.casestudy.onlinebanking.service.DepositorsService;
import com.ibm.javabootcamp.casestudy.onlinebanking.service.DepositorsServiceImpl;

@Path("/depositor")
public class DepositorsController {

	private DepositorsService depositorsService;

	public DepositorsController() {
		this.depositorsService = new DepositorsServiceImpl();

	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		return "HELLO WORLD";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Depositors> getDepositors() {

		try {
			
			List<Depositors> depositors;

			depositors = depositorsService.showDetails();

			return depositors;

		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@GET
	@Path("{dep_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Depositors getDepositors(@PathParam("dep_id") String depId) {
		
		try {
			
			Long longDepId = Long.parseLong(depId);
			Depositors depositor = depositorsService.find(longDepId);
			
			return depositor;
			
		}catch(Exception e){
			
			throw new WebApplicationException(e);
			
		}
	}
	

}
