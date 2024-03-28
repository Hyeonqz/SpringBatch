package org.example.batch_ex1.domain.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.example.batch_ex1.domain.batch.JobExecution;
import org.example.batch_ex1.domain.batch.enums.BatchStatus;
import org.example.batch_ex1.domain.customer.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
public class DocumentBatchJob {
	private final CustomerRepository customerRepository;
	private final EmailProvider emailProvider;

	public DocumentBatchJob(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.emailProvider = new EmailProvider.Fake();
	}

	public JobExecution execute() {
		JobExecution jobExecution = new JobExecution();
		jobExecution.setStatus(BatchStatus.STARTING);
		jobExecution.setStartTime(LocalDateTime.now());

		int pageNo = 0;

		try {
			while (true) {
				// 1. 유저를 조회한다.
				final PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by("id").ascending());

				final Page<Customer> page = customerRepository.findAll(pageRequest);

				final Customer customer;

				if(page.isEmpty()) {
					break;
				} else {
					pageNo++;
					customer = page.getContent().get(0);
				}

				// 2. 휴먼 계정 대상을 추출 및 변환한다.
				//  로그인 날짜 / 365일 전 / 오늘
				final boolean isDormanTarget = LocalDate.now()
					.minusDays(365)
					.isAfter(customer.getLoginAt().toLocalDate());

				if(isDormanTarget) {
					customer.setStatus(Status.DORMANT);
				} else {
					continue;
				}

				// 3. 휴먼 계정으로 상태를 변경한다.
				customerRepository.save(customer);

				// 4. 휴먼 계정이 되었다고 메일을 보낸다
				emailProvider.send(customer.getEmail(), "휴먼전화 안내 메일 입니다", "휴먼전환 된다 ㅅㄱ");
			}
			jobExecution.setStatus(BatchStatus.COMPLETED);
		} catch (Exception e) {
			jobExecution.setStatus(BatchStatus.FAILED);
		}
		jobExecution.setEndTime(LocalDateTime.now());

		emailProvider.send(
			"admin@naver.com",
			"배치 완료 알림",
			"DormantBatchJob 이 수행되었습니다 : " + jobExecution.getStatus() );

		return jobExecution;
	}
}
