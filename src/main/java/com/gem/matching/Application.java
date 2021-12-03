package com.gem.matching;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class Application {

  /**
   * Main method to start the spring boot app for Guest MR Query Service.
   *
   * @param args Application arguments
   */
  public static void main(String[] args) {

    System.setProperty("aws.paramstore.enabled", "false");
    SpringApplication.run(Application.class, args);
  }
}
