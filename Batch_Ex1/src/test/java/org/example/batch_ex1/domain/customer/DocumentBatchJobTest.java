package org.example.batch_ex1.domain.customer;

import java.time.LocalDateTime;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.example.batch_ex1.domain.batch.Job;
import org.example.batch_ex1.domain.batch.JobExecution;
import org.example.batch_ex1.domain.batch.TaskletJob;
import org.example.batch_ex1.domain.batch.enums.BatchStatus;
import org.example.batch_ex1.domain.customer.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DocumentBatchJobTest {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private Job job;

	@BeforeEach
	public void setup() {
		customerRepository.deleteAll();
	}

	@Test
	@DisplayName("로그인 시간이 일년을 경과한 고객이 세명이고, 일년 이내에 로그인한 고객이 다섯명이면 3명의 고객이 휴먼전화 대상자이다.")
	void test1() {
		// given
		saveCustomer(366);
		saveCustomer(366);
		saveCustomer(366);
		saveCustomer(364);
		saveCustomer(363);
		saveCustomer(362);
		saveCustomer(361);
		saveCustomer(360);

		// when
		final JobExecution result = job.execute();

		// then
		final long dormantCount = customerRepository.findAll()
			.stream()
			.filter(it -> it.getStatus() == Status.DORMANT)
			.count();

		Assertions.assertThat(dormantCount).isEqualTo(3);
		Assertions.assertThat(result.getStatus()).isEqualTo(BatchStatus.COMPLETED);

	}

	@Test
	@DisplayName("고객이 10명이지만, 모두 다 휴먼전화 대상이 아니면 휴먼전환 대상은 10명이다.")
	void test2() {
		// given
		saveCustomer(400);
		saveCustomer(400);
		saveCustomer(400);
		saveCustomer(400);
		saveCustomer(400);
		saveCustomer(400);
		saveCustomer(400);
		saveCustomer(400);
		saveCustomer(400);
		saveCustomer(400);

		// when
		JobExecution result = job.execute();

		// then
		final long dormantCount = customerRepository.findAll()
			.stream()
			.filter(it -> it.getStatus() == Status.DORMANT)
			.count();

		Assertions.assertThat(dormantCount).isEqualTo(10);
		Assertions.assertThat(result.getStatus()).isEqualTo(BatchStatus.COMPLETED);

	}

	@Test
	@DisplayName("고객이 없는 경우에도 배치는 정상작동 해야한다.")
	void test3() {
		// when
		final JobExecution result = job.execute();

		// then
		final long dormantCount = customerRepository.findAll()
			.stream()
			.filter(it -> it.getStatus() == Status.DORMANT)
			.count();

		Assertions.assertThat(dormantCount).isEqualTo(0);
		Assertions.assertThat(result.getStatus()).isEqualTo(BatchStatus.COMPLETED);

	}

	@Test
	@DisplayName("배치가 실패하면 BatchStatus 는 FAILED 를 반환해야 한다.")
	void test4() {

		// given
		final Job job = new TaskletJob(null);

		// when
		final JobExecution result = job.execute();

		// then
		Assertions.assertThat(result.getStatus()).isEqualTo(BatchStatus.FAILED);
	}

	private void saveCustomer(long loginMinusDays) {
		final String uuid = UUID.randomUUID().toString();
		final Customer customer = new Customer(uuid, uuid + "@naver.com");
		customer.setLoginAt(LocalDateTime.now().minusDays(loginMinusDays));
		customerRepository.save(customer);
	}
}