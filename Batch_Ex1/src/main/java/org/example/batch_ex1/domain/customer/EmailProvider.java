package org.example.batch_ex1.domain.customer;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

public interface EmailProvider {

	public void send(String email, String title, String body);
	@Slf4j
	class Fake implements EmailProvider {

		@Override
		public void send(String email, String title, String body) {
			log.info("이메일 전송 완료 {} : {} : {}",email,title,body);
		}
	}

}


