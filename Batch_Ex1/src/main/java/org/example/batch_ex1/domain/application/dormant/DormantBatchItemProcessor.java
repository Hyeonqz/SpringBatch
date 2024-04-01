package org.example.batch_ex1.domain.application.dormant;

import java.time.LocalDate;
import org.example.batch_ex1.domain.batch.ItemProcessor;
import org.example.batch_ex1.domain.customer.Customer;
import org.example.batch_ex1.domain.customer.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchItemProcessor implements ItemProcessor<Customer, Customer> {

  @Override
  public Customer process(Customer item) {

    final boolean isDormantTarget = LocalDate.now()
            .minusDays(365)
            .isAfter(item.getLoginAt().toLocalDate());

        if (isDormantTarget) {
          item.setStatus(Status.DORMANT);
          return item;
        } else {
          return null;
        }
  }

}
