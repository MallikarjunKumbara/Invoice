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
        return ResponseEntity.status(HttpStatus.CREATED).body("Invoice created successfully");
    }

	 @GetMapping("/all")
	    public ResponseEntity<List<Invoice>> getAllInvoices() {
	        List<Invoice> invoices = invoiceService.getAllInvoices();
	        return ResponseEntity.ok().body(invoices);
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
	        return ResponseEntity.ok().body("Invoice updated successfully");
	    }

	 @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
	        invoiceService.deleteInvoice(id);
	        return ResponseEntity.ok().body("Invoice deleted successfully");
	    }

	// Other methods for invoice management
}
