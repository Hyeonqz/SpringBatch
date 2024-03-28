package org.example.batch_ex1.domain.batch;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Objects;
import lombok.Builder;
import org.example.batch_ex1.domain.batch.JobExecution;
import org.example.batch_ex1.domain.batch.JobExecutionListener;
import org.example.batch_ex1.domain.batch.Tasklet;
import org.example.batch_ex1.domain.batch.enums.BatchStatus;
import org.example.batch_ex1.domain.customer.Customer;
import org.example.batch_ex1.domain.customer.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

public class Job {
	private final Tasklet tasklet;
	private final JobExecutionListener jobExecutionListener;

	public Job(Tasklet tasklet) {
		this(tasklet,null);
	}

	@Builder
	public Job(ItemReader<?> itemReader, ItemProcessor<?,?> itemProcessor, ItemWriter<?> itemWriter, JobExecutionListener jobExecutionListener) {
		this(new SimpleTasklet(itemReader, itemProcessor, itemWriter), null);
	}

	public Job(Tasklet tasklet, JobExecutionListener jobExecutionListener) {
		this.tasklet = tasklet;
    this.jobExecutionListener = Objects.requireNonNullElseGet(jobExecutionListener,
        () -> new JobExecutionListener() {
          @Override
          public void beforeJob(JobExecution jobExecution) {
          }

          @Override
          public void afterJob(JobExecution jobExecution) {
          }
        });
	}



	public JobExecution execute() {

		final JobExecution jobExecution = new JobExecution();
		jobExecution.setStatus(BatchStatus.STARTING);
		jobExecution.setStartTime(LocalDateTime.now());

		/* 전 처리 */
		jobExecutionListener.beforeJob(jobExecution);

		try {
			tasklet.execute();
			jobExecution.setStatus(BatchStatus.COMPLETED);

		} catch (Exception e) {
			jobExecution.setStatus(BatchStatus.FAILED);
		}

		jobExecution.setEndTime(LocalDateTime.now());

		/* 비즈니스 로직 후 처리 */
		jobExecutionListener.afterJob(jobExecution);

		return jobExecution;
	}
}
