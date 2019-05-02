package com.ibm.javabootcamp.casestudy.onlinebanking.restcontroller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Merchants;
import com.ibm.javabootcamp.casestudy.onlinebanking.service.MerchantsService;
import com.ibm.javabootcamp.casestudy.onlinebanking.service.MerchantsServiceImpl;

@Path("/merchants")
public class MerchantsController {
	
	private MerchantsService merchantsService;
	
	public MerchantsController() {
		this.merchantsService = new MerchantsServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Merchants> getMerchants(
			@QueryParam("merch_name") String merchName){
		try {
			List<Merchants> merchants;
			
			if(StringUtils.isAllBlank(merchName)) {
				merchants = merchantsService.findAll();
			}else {
				merchants = merchantsService.findByName(merchName);
			}
			
			return merchants;
		}catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMerchant(Merchants merchant){
		
		try {
			merchantsService.addMerchant(merchant);
			String result = "Merchant " + merchant.getMerch_name() + " saved";
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
		
	}
	
}
