package org.example.batch_ex1.domain.batch;

import org.springframework.stereotype.Component;

@Component
public class SimpleTasklet<I,O> implements Tasklet {
  private final ItemReader<I> itemReader;
  private final ItemProcessor<I,O> itemProcessor;
  private final ItemWriter<O> itemWriter;

  public SimpleTasklet(ItemReader<I> itemReader, ItemProcessor<I, O> itemProcessor,
      ItemWriter<O> itemWriter) {
    this.itemReader = itemReader;
    this.itemProcessor = itemProcessor;
    this.itemWriter = itemWriter;
  }


  @Override
  public void execute() {
    // 비즈니스 로직
    int pageNo = 0;

      while (true) {
        // Read
        final I read = itemReader.read();
        if(read == null) {
          break;
        }
        // Process
        final O process = itemProcessor.process(read);
        if(process == null) {
          continue;
        }
        // Write
        itemWriter.write(process);
      }
    }

}
