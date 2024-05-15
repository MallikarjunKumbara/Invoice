package com.example.invoiceapp.controller;

import com.example.invoiceapp.dto.InvoiceDTO;
import com.example.invoiceapp.entity.Invoice;
import com.example.invoiceapp.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@PostMapping("/create")
	public ResponseEntity<String> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
		invoiceService.createInvoice(invoiceDTO);
		return new ResponseEntity<>("Invoice created successfully", HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		List<Invoice> invoices = invoiceService.getAllInvoices();
		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
		Invoice invoice = invoiceService.getInvoiceById(id);
		if (invoice != null) {
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) {
		invoiceService.updateInvoice(id, invoiceDTO);
		return new ResponseEntity<>("Invoice updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
		invoiceService.deleteInvoice(id);
		return new ResponseEntity<>("Invoice deleted successfully", HttpStatus.OK);
	}

	// Other methods for invoice management
}
