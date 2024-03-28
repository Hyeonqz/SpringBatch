package org.example.batch_ex1.domain.application;

import org.example.batch_ex1.domain.batch.Job;
import org.example.batch_ex1.domain.batch.SimpleTasklet;
import org.example.batch_ex1.domain.customer.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchJob(
        DormantBatchItemReader itemReader,
        DormantBatchItemProcessor itemProcessor,
        DormantBatchItemWriter itemWriter,
        DormantBatchJobExecutionListener dormantBatchJobExecutionListener) {

      return Job.builder()
          .itemReader(itemReader)
          .itemProcessor(itemProcessor)
          .itemWriter(itemWriter)
          .jobExecutionListener(dormantBatchJobExecutionListener)
          .build();
    }

}
