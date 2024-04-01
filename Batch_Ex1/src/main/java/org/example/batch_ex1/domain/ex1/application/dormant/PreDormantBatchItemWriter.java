package org.example.batch_ex1.domain.ex1.application.dormant;

import org.example.batch_ex1.domain.ex1.application.batch.ItemWriter;
import org.example.batch_ex1.domain.ex1.application.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class PreDormantBatchItemWriter implements ItemWriter<Customer> {
	@Override
	public void write(Customer item) {

	}
}
