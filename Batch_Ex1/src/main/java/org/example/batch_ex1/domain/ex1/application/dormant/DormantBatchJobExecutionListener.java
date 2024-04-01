package org.example.batch_ex1.domain.ex1.application.dormant;

import org.example.batch_ex1.domain.ex1.application.batch.JobExecution;
import org.example.batch_ex1.domain.ex1.application.batch.JobExecutionListener;
import org.example.batch_ex1.domain.ex1.application.customer.EmailProvider;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchJobExecutionListener implements JobExecutionListener {
	private final EmailProvider emailProvider;

	public DormantBatchJobExecutionListener() {
		this.emailProvider = new EmailProvider.Fake();
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// 비즈니스 로직
		emailProvider.send(
			"admin@naver.com",
			"배치 완료 알림",
			"DormantBatch 가 수행되었습니다. status :  " + jobExecution.getStatus()
		);
	}
}
