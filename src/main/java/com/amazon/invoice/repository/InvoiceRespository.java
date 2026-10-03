package com.amazon.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.amazon.invoice.entity.InvoiceEntity;

@Repository
public interface InvoiceRespository extends JpaRepository<InvoiceEntity, Integer> {
	

}
