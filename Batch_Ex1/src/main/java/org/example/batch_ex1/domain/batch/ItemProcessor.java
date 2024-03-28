package org.example.batch_ex1.domain.batch;

import org.springframework.stereotype.Component;

@Component
public interface ItemProcessor<I,O> {
  O process(I item);
}
