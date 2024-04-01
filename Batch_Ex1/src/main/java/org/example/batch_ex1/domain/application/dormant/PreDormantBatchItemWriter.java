package org.example.batch_ex1.domain.application.dormant;

import org.example.batch_ex1.domain.batch.ItemWriter;
import org.example.batch_ex1.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class PreDormantBatchItemWriter implements ItemWriter<Customer> {
	@Override
	public void write(Customer item) {

	}
}
