package com.batch1.customerSpringService.invoices.services;

import java.util.List;

import com.batch1.customerSpringService.invoices.entities.Invoice;

public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Long invoiceId);
    Invoice saveInvoice(Invoice invoice);
    void deleteInvoice(Long invoiceId);
}
