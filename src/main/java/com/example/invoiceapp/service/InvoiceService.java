package com.example.invoiceapp.service;

import com.example.invoiceapp.dto.InvoiceDTO;
import com.example.invoiceapp.entity.Invoice;
import com.example.invoiceapp.entity.User;
import com.example.invoiceapp.exception.InvalidInvoiceException;
import com.example.invoiceapp.exception.ResourceNotFoundException;
import com.example.invoiceapp.repository.InvoiceRepository;
import com.example.invoiceapp.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

	@Autowired
	private   InvoiceRepository invoiceRepository;
	@Autowired
	private UserRepository userRepository;

	public void createInvoice(InvoiceDTO invoiceDTO) throws InvalidInvoiceException {
        // Validate invoice data
        if (invoiceDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0
                || invoiceDTO.getAmount().compareTo(new BigDecimal("3000")) < 0) {
            throw new InvalidInvoiceException("Invalid invoice amount. Amount must be positive and minimum INR 3000.");
        }

        try {
            // Fetch user from repository using userId
            Long userId = invoiceDTO.getUserId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

            // Create Invoice entity and set values from DTO
            Invoice invoice = new Invoice();
            invoice.setClientName(invoiceDTO.getClientName());
            invoice.setAmount(invoiceDTO.getAmount());
            invoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
            invoice.setDescription(invoiceDTO.getDescription());
            invoice.setUser(user);

            // Save the invoice
            invoiceRepository.save(invoice);
        } catch (Exception e) {
            throw new InvalidInvoiceException("Failed to create invoice: " + e.getMessage());
        }
    }
	 
	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

	public Invoice getInvoiceById(Long id) {
		return invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
	}

	public void updateInvoice(Long id, InvoiceDTO invoiceDTO) 
	{
		// Check if the invoice exists
		Invoice existingInvoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));

		// Validate invoice data
		if (invoiceDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0
				|| invoiceDTO.getAmount().compareTo(new BigDecimal("3000")) < 0) {
			throw new InvalidInvoiceException("Invalid invoice amount. Amount must be positive and minimum INR 3000.");
		}
		
		if (invoiceDTO.getInvoiceDate().isAfter(LocalDate.now())) {
			throw new InvalidInvoiceException("Invalid invoice date. Future dates are not allowed.");
		}

		// Update the invoice
		existingInvoice.setClientName(invoiceDTO.getClientName());
		existingInvoice.setAmount(invoiceDTO.getAmount());
		existingInvoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
		existingInvoice.setDescription(invoiceDTO.getDescription());
		invoiceRepository.save(existingInvoice);
	}

	public void deleteInvoice(Long id) {
		// Check if the invoice exists
		Invoice existingInvoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));

		// Delete the invoice
		invoiceRepository.delete(existingInvoice);
	}

	// Other methods for invoice management
}