package org.example.batch_ex1.domain.ex1.application.batch;

import org.springframework.stereotype.Component;

@Component
public interface JobExecutionListener {
	void beforeJob(JobExecution jobExecution);

	void afterJob(JobExecution jobExecution);
}
