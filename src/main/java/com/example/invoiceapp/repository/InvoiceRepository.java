package com.example.invoiceapp.repository;

import com.example.invoiceapp.entity.Invoice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByClientName(String clientName);
}
