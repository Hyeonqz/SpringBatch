package org.example.batch_ex1.domain.batch;

import org.springframework.stereotype.Component;

@Component
public interface ItemWriter<O> {
  void write(O item);
}
