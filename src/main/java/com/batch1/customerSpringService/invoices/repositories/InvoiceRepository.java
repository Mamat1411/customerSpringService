package com.batch1.customerSpringService.invoices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.batch1.customerSpringService.invoices.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
    @Query(value = "select * from invoices where invoice_id =?1", nativeQuery = true)
    Invoice getInvoiceById(Long invoiceId);
}
