package org.camunda.community.migration.example.loadbalancer;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  public static class MyCommandListener implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
      System.out.println("hello world");
    }
  }
}