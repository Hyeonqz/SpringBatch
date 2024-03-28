package org.example.batch_ex1.domain.batch;

import java.time.LocalDateTime;

import org.example.batch_ex1.domain.batch.enums.BatchStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
public class JobExecution {
	private BatchStatus status;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
