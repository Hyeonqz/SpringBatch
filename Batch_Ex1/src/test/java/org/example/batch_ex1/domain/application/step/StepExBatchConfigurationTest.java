package org.example.batch_ex1.domain.application.step;

import org.example.batch_ex1.domain.batch.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StepExBatchConfigurationTest {

  @Autowired
  private Job stemExampleBatchJob;

  @Test
  void test() {
    stemExampleBatchJob.execute();
  }



}