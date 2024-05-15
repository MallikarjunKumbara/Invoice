package com.example.invoiceapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class InvoiceDTO {
    private String clientName;
    private BigDecimal amount;
    private LocalDate invoiceDate;
    private String description;

   
}
