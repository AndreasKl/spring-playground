package net.andreaskluth.batch;

import java.util.Collections;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderConfiguration {

  @Bean
  @StepScope
  public ItemReader<String> reader(@Value("#{jobParameters[hello]}") String text) {
    System.out.println("Reader JobParam: " + text);

    return new ListItemReader<>(Collections.singletonList("Reader"));
  }

}
