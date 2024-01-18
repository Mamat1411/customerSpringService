package com.batch1.customerSpringService.invoices.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batch1.customerSpringService.invoices.dtos.requests.InvoiceRequestDto;
import com.batch1.customerSpringService.invoices.dtos.response.InvoiceResponseDto;
import com.batch1.customerSpringService.invoices.entities.Invoice;
import com.batch1.customerSpringService.invoices.services.InvoiceService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public ResponseEntity<?> getAllInvoices() {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<Invoice> invoices = invoiceService.getAllInvoices();
            List<InvoiceResponseDto> invoiceResponseDto = invoices.stream()
                    .map(invoice -> modelMapper.map(invoice, InvoiceResponseDto.class)).collect(Collectors.toList());

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", invoiceResponseDto);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<?> getInvoiceById(@PathVariable("invoiceId") Long invoiceId) {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Invoice invoice = invoiceService.getInvoiceById(invoiceId);
            InvoiceResponseDto invoiceResponseDto = modelMapper.map(invoice, InvoiceResponseDto.class);

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", invoiceResponseDto);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    public ResponseEntity<?> saveInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Invoice invoice = modelMapper.map(invoiceRequestDto, Invoice.class);
            invoiceService.saveInvoice(invoice);

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", invoice);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{invoiceId}")
    public ResponseEntity<?> updateInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceRequestDto invoiceRequestDto) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Invoice invoice = invoiceService.getInvoiceById(invoiceId);
            invoice.setInvoiceDate(invoiceRequestDto.getInvoiceDate());
            invoice.setDueDate(invoiceRequestDto.getDueDate());
            invoice.setAmount(invoiceRequestDto.getAmount());
            invoice.setTax(invoiceRequestDto.getTax());
            invoice.setDiscount(invoiceRequestDto.getDiscount());
            invoice.setAmountDue(invoiceRequestDto.getAmountDue());
            invoice.setPaymentStatus(invoiceRequestDto.getPaymentStatus());
            invoice.setBookingId(invoiceRequestDto.getBookingId());
            invoiceService.saveInvoice(invoice);

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");
            resultMap.put("Data", invoice);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");
            resultMap.put("Data", null);

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<?> deleteBooking(@PathVariable("invoiceId") Long invoiceId){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Invoice invoice = invoiceService.getInvoiceById(invoiceId);
            invoiceService.deleteInvoice(invoice.getInvoiceId());

            resultMap.put("Status", "200");
            resultMap.put("Message", "success");

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("Status", "500");
            resultMap.put("Message", "Internal Server Error");

            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
