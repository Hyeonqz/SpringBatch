package org.example.batch_ex1.domain.customer;

import java.security.Identity;
import java.time.LocalDateTime;

import org.example.batch_ex1.domain.customer.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter @ToString
public class Customer {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private LocalDateTime createAt;
	@Setter
	private LocalDateTime loginAt;
	@Setter
	private Status status;

	public Customer(String name, String email) {
		this.name=name;
		this.email=email;
		this.createAt = LocalDateTime.now();
		this.loginAt = LocalDateTime.now();
		this.status = Status.NORMAL;
	}

}
