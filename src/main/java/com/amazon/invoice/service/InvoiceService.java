package com.amazon.invoice.service;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import com.amazon.invoice.entity.InvoiceEntity;
import com.amazon.invoice.repository.InvoiceRespository;
import com.amazon.invoice.request.response.InvoiceRequest;
import com.amazon.invoice.request.response.InvoiceResponse;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRespository invoiceRespository;

	public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
		InvoiceEntity entity = new InvoiceEntity();

		entity.setCustomerName(invoiceRequest.getCustomerName());
		entity.setGst(invoiceRequest.getGst());
		entity.setStatus("PAID");
		entity.setInvId("INV1234");
		
		entity = invoiceRespository.save(entity);
		InvoiceResponse invoiceResponse = new InvoiceResponse();
		if (entity.getId() > 0) {
			invoiceResponse.setCustomerName(entity.getCustomerName());
			invoiceResponse.setGst(entity.getGst());
			invoiceResponse.setStatus(entity.getStatus());
			invoiceResponse.setInvId(entity.getInvId());
			invoiceResponse.setId(entity.getId());
		}
		return invoiceResponse;
	}
	
	
	public List<InvoiceResponse> getInvoices(int page, int size){
		
		
		
		PageRequest pageable = PageRequest.of(page, size);
		 org.springframework.data.domain.Page<InvoiceEntity> all = invoiceRespository.findAll(pageable);
		
		for(InvoiceEntity invoiceEntity : all)
		{
			InvoiceResponse invoiceResponse= new InvoiceResponse();
			
			invoiceResponse.setId(invoiceEntity.getId());
			invoiceResponse.setInvId(invoiceEntity.getInvId());
			invoiceResponse.setCustomerName(invoiceEntity.getCustomerName());
			invoiceResponse.setGst(invoiceEntity.getGst());
			invoiceResponse.setStatus(invoiceEntity.getStatus());
			
		}
		return null;
		
	}
	

}