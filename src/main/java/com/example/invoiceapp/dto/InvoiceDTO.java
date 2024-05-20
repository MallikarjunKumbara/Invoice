package com.example.invoiceapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvoiceDTO {
    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotNull(message = "Amount must be specified")
    @Positive(message = "Amount must be a positive value")
    private BigDecimal amount;

    private LocalDate invoiceDate;
    private String description;

    @NotNull(message = "User ID is required")
    private Long userId;
}
