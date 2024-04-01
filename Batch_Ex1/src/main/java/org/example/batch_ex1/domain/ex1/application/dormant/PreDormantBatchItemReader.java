package org.example.batch_ex1.domain.ex1.application.dormant;

import org.example.batch_ex1.domain.ex1.application.batch.ItemReader;
import org.example.batch_ex1.domain.ex1.application.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class PreDormantBatchItemReader implements ItemReader<Customer> {
	@Override
	public Customer read() {
		return null;
	}
}
