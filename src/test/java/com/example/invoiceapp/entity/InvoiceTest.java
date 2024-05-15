package com.example.invoiceapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvoiceTest {

    @Test
    public void testId() {
        
        Invoice invoice = new Invoice();

        Long id = 123L;
        invoice.setId(id);

        assertEquals(id, invoice.getId(), "ID should match");
    }

    @Test
    public void testClient() {
        
        Invoice invoice = new Invoice();

        String clientName = "xyz";
        invoice.setClientName(clientName);

        assertEquals(clientName, invoice.getClientName(), "Client name should match");
    }

    @Test
    public void testAmount() {
        
        Invoice invoice = new Invoice();

        BigDecimal amount = BigDecimal.valueOf(5000.00);
        invoice.setAmount(amount);

        assertEquals(amount, invoice.getAmount(), "Amount should match");
    }

    @Test
    public void testInvoiceDate() {
       
        Invoice invoice = new Invoice();

        LocalDate invoiceDate = LocalDate.of(2024, 5, 15);
        invoice.setInvoiceDate(invoiceDate);

        assertEquals(invoiceDate, invoice.getInvoiceDate(), "Invoice date should match");
    }

    @Test
    public void testDescription() {
        
        Invoice invoice = new Invoice();

        String description = "Invoice for services rendered";
        invoice.setDescription(description);

        assertEquals(description, invoice.getDescription(), "Description should match");
    }

  
}
