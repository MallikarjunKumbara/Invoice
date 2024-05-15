package com.example.invoiceapp.service;

import com.example.invoiceapp.dto.InvoiceDTO;
import com.example.invoiceapp.entity.Invoice;
import com.example.invoiceapp.exception.InvalidInvoiceException;
import com.example.invoiceapp.repository.InvoiceRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	public void createInvoice(InvoiceDTO invoiceDTO) throws InvalidInvoiceException {
		try {
			Invoice invoice = new Invoice();
			invoice.setClientName(invoiceDTO.getClientName());
			invoice.setAmount(invoiceDTO.getAmount());
			invoice.setInvoiceDate(LocalDate.now());
			invoice.setDescription(invoiceDTO.getDescription());
			// Set user for the invoice if needed
			invoiceRepository.save(invoice);
		} catch (Exception e) {
			throw new InvalidInvoiceException("Failed to create invoice: " + e.getMessage());
		}
	}

	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

	public Invoice getInvoiceById(Long id) {
		return invoiceRepository.findById(id).orElse(null);
	}

	public void updateInvoice(Long id, InvoiceDTO invoiceDTO) {
		Invoice existingInvoice = invoiceRepository.findById(id).orElse(null);
		if (existingInvoice != null) {
			existingInvoice.setClientName(invoiceDTO.getClientName());
			existingInvoice.setAmount(invoiceDTO.getAmount());
			existingInvoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
			existingInvoice.setDescription(invoiceDTO.getDescription());
			// Set user for the invoice if needed
			invoiceRepository.save(existingInvoice);
		}
	}

	public void deleteInvoice(Long id) {
		invoiceRepository.deleteById(id);
	}

	// Other methods for invoice management
}
