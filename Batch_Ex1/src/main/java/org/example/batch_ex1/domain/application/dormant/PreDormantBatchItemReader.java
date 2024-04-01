package org.example.batch_ex1.domain.application.dormant;

import org.example.batch_ex1.domain.batch.ItemReader;
import org.example.batch_ex1.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class PreDormantBatchItemReader implements ItemReader<Customer> {
	@Override
	public Customer read() {
		return null;
	}
}
