package org.example.batch_ex1.domain.ex1.application.dormant;

import java.time.LocalDate;

import org.example.batch_ex1.domain.ex1.application.batch.ItemProcessor;
import org.example.batch_ex1.domain.ex1.application.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class PreDormantBatchItemProcessor implements ItemProcessor<Customer, Customer> {
	@Override
	public Customer process(Customer customer) {
		final LocalDate targetDate = LocalDate.now().minusDays(365).plusDays(7);

		if (targetDate.equals(customer.getLoginAt().toLocalDate())) {
			return customer;
		} else {
			return null;
		}
	}

}
