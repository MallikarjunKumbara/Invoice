package com.example.invoiceapp.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.invoiceapp.entity.Invoice;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
//@ActiveProfiles("test")
public class InvoiceRepositoryTest {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Test
	@Order(1)

	public void testSaveInvoice() {
		// JUnit test for saveInvoice
		Invoice invoice = new Invoice();
		invoice.setClientName("Client ABC");
		invoice.setAmount(BigDecimal.valueOf(1000));
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setDescription("Test invoice");

		invoiceRepository.save(invoice);

		Assertions.assertThat(invoice.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void testGetInvoice() {
		Invoice invoice = invoiceRepository.findById(1L).get();
		Assertions.assertThat(invoice.getId()).isEqualTo(1L);
	}

	@Test
	@Order(3)
	public void testListOfGetInvoice() {
		List<Invoice> invoices = invoiceRepository.findAll();
		Assertions.assertThat(invoices.size()).isGreaterThan(0);
	}

	@Test
	@Order(4)

	public void updateGetInvoice() {
		Invoice invoice = invoiceRepository.findById(1L).get();
		invoice.setClientName("Mallikarjun");
		Invoice invoiceUpdated = invoiceRepository.save(invoice);
		Assertions.assertThat(invoiceUpdated.getClientName()).isEqualTo("Mallikarjun");
	}

	@Test
	@Order(5)

	public void deleteGetInvoice() {
		Invoice invoice = invoiceRepository.findById(1L).get();
		invoiceRepository.delete(invoice);

		// delete by id
		// invoiceRepository.deleteById(1L);

		Invoice invoice1 = null;
		Optional<Invoice> optionalInvoice = invoiceRepository.findByClientName("Mallikarjun");

		if (optionalInvoice.isPresent()) {
			invoice1 = optionalInvoice.get();
		}
	}

}
