package net.andreaskluth.batch;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfiguration {

  @Bean
  @StepScope
  public ItemWriter<String> writer(@Value("#{jobParameters[hello]}") String text) {
    System.out.println("Writer JobParam: " + text);

    return new ItemWriter<String>() {
      @Override
      public void write(List<? extends String> value) throws Exception {
        System.out.println("From Reader: " + value);
      }
    };
  }

}
