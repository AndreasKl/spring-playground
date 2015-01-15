package net.andreaskluth.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  private RedisTemplate<String, CanHazCheeseburgers> redisTemplate;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args).close();
  }

  public void run(String... args) throws Exception {
    CanHazCheeseburgers canHazCheeseburgers = new CanHazCheeseburgers("CAT", "DOG", "CHEEZ");
    redisTemplate.opsForList().leftPush("chhez", canHazCheeseburgers);
  }

}
