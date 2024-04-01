package org.example.batch_ex1.domain.ex1.application.dormant;

import org.example.batch_ex1.domain.ex1.application.batch.ItemWriter;
import org.example.batch_ex1.domain.ex1.application.customer.Customer;
import org.example.batch_ex1.domain.ex1.application.customer.CustomerRepository;
import org.example.batch_ex1.domain.ex1.application.customer.EmailProvider;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchItemWriter implements ItemWriter<Customer> {
	private final CustomerRepository customerRepository;
	private final EmailProvider emailProvider;

	public DormantBatchItemWriter(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.emailProvider = new EmailProvider.Fake();
	}

	@Override
	public void write(Customer item) {
		customerRepository.save(item);
		emailProvider.send(item.getEmail(), "휴먼전화 안내 이메일 입니다.", "로그인 안하면 휴먼전환이다 임마");
	}
}
