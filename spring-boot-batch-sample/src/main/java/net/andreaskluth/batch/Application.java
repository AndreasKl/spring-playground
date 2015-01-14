package net.andreaskluth.batch;

import java.util.Collections;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(Application.class);
    app.setDefaultProperties(Collections.singletonMap("spring.batch.job.enabled", false));

    try (ConfigurableApplicationContext ctx = app.run(args)) {
      launchJob(ctx);
    }
  }

  private static void launchJob(ConfigurableApplicationContext ctx) throws Exception {
    JobParameters jobParameters = new JobParameters(Collections.singletonMap("hello", new JobParameter("Hallo Welt")));
    JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
    jobLauncher.run(ctx.getBean(Job.class), jobParameters);
  }
}
