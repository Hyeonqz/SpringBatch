package org.example.batch_ex1.domain.application.dormant;

import org.example.batch_ex1.domain.batch.ItemReader;
import org.example.batch_ex1.domain.customer.Customer;
import org.example.batch_ex1.domain.customer.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchItemReader implements ItemReader<Customer> {

  private int pageNo = 0;
  private final CustomerRepository customerRepository;

  public DormantBatchItemReader(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  // 비즈니스 로직에서 배치를 담당할 것.

  @Override
  public Customer read() {

        final PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by("id").ascending());
        final Page<Customer> page = customerRepository.findAll(pageRequest);

        final Customer customer;

        if (page.isEmpty()) {
          pageNo = 0;
          return null;
        } else {
          pageNo++;
          return page.getContent().get(0);
        }
  }
}
