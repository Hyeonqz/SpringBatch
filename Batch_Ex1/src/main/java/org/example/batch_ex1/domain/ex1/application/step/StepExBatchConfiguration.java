package org.example.batch_ex1.domain.ex1.application.step;

import org.example.batch_ex1.domain.ex1.application.batch.Job;
import org.example.batch_ex1.domain.ex1.application.batch.Step;
import org.example.batch_ex1.domain.ex1.application.batch.StepJobBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepExBatchConfiguration {

	@Bean
	public Job stemExampleBatchJob(
		Step step1,
		Step step2,
		Step step3) {

		return new StepJobBuilder()
			.start(step1)
			.next(step2)
			.next(step3)
			.build();
	}

	@Bean
	public Step step1() {
		return new Step(() -> System.out.println("STEP-1"));
	}

	@Bean
	public Step step2() {
		return new Step(() -> System.out.println("STEP-2"));

	}

	@Bean
	public Step step3() {
		return new Step(() -> System.out.println("STEP-3"));
	}

}
