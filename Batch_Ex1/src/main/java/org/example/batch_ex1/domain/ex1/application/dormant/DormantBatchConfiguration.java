package org.example.batch_ex1.domain.ex1.application.dormant;

import org.example.batch_ex1.domain.ex1.application.batch.Job;
import org.example.batch_ex1.domain.ex1.application.batch.Step;
import org.example.batch_ex1.domain.ex1.application.batch.StepJobBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

	@Bean
	public Job dormantBatchJob(
		Step preDormantBatchStep,
		Step dormantBatchStep,
		DormantBatchJobExecutionListener dormantBatchJobExecutionListener) {

		return new StepJobBuilder()
			.start(preDormantBatchStep)
			.next(dormantBatchStep)
			.build();
	}

	@Bean
	public Step dormantBatchStep(
		DormantBatchItemReader itemReader,
		DormantBatchItemProcessor itemProcessor,
		DormantBatchItemWriter itemWriter) {
		return Step.builder()
			.itemReader(itemReader)
			.itemProcessor(itemProcessor)
			.itemWriter(itemWriter)
			.build();
	}

	@Bean
	public Step preDormantBatchStep(
		PreDormantBatchItemReader itemReader,
		PreDormantBatchItemProcessor itemProcessor,
		PreDormantBatchItemWriter itemWriter) {

		return Step.builder()
			.itemReader(itemReader)
			.itemProcessor(itemProcessor)
			.itemWriter(itemWriter)
			.build();
	}

	// 휴먼전화 예정 1주일전 사람에게 이메일을 발송한다.

}
