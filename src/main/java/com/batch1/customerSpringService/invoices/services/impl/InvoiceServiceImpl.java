package com.batch1.customerSpringService.invoices.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch1.customerSpringService.invoices.entities.Invoice;
import com.batch1.customerSpringService.invoices.repositories.InvoiceRepository;
import com.batch1.customerSpringService.invoices.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(Long invoiceId) {
        return invoiceRepository.getInvoiceById(invoiceId);
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }
    
}
