package com.gyeongju.dogshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DogshowApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =
            SpringApplication.run(DogshowApplication.class, args);
  }

}
