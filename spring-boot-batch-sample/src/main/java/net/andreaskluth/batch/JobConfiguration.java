package net.andreaskluth.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

  @Bean
  public Step step1(StepBuilderFactory steps, ItemReader<String> reader, ItemWriter<String> writer) throws Exception {
    return steps.get("step1").<String, String> chunk(0).reader(reader).writer(writer).build();
  }

  @Bean
  public Job job(JobBuilderFactory jobs, Step step1) throws Exception {
    return jobs.get("job").start(step1).build();
  }

}
