package org.example.batch_ex1.domain.ex1.application.batch.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BatchStatus {
	STARTING, FAILED, COMPLETED
}
