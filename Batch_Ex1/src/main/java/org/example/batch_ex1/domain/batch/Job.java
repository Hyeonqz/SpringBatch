package org.example.batch_ex1.domain.batch;

import org.springframework.stereotype.Component;

@Component
public interface Job {

  JobExecution execute();
}
