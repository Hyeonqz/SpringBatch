package org.example.batch_ex1.domain.application.dormant;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.example.batch_ex1.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PreDormantBatchItemProcessorTest {

	private PreDormantBatchItemProcessor itemProcessor;

	@BeforeEach
	void setUp() {
		itemProcessor = new PreDormantBatchItemProcessor();
	}

	@Test
	@DisplayName("로그인 날짜가 오늘로부터 358일 전이면 customer 를 반환해야 한다.")
	void test1() {
		//given
		final Customer customer = new Customer("hyeonq", "QQQ@naver.com");

		// 오늘은 2023.06.04 ,  예정자는 2022.06.11
		customer.setLoginAt(LocalDateTime.now().minusDays(365).plusDays(7));

		//when
		final Customer result = itemProcessor.process(customer);

		//then
		Assertions.assertThat(result).isEqualTo(customer);
		Assertions.assertThat(result).isNotNull();
	}

	@Test
	@DisplayName("로그인 날짜가 오늘로부터 358일 전이 아니면 null 을 반환한다.")
	void test2() {
		// given
		final Customer customer = new Customer("hyeonq", "QQQ@naver.com");

		// when
		final Customer result = itemProcessor.process(customer);

		// then
		Assertions.assertThat(result).isNull();
	}

}