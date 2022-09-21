package com.tomspencerlondon;

import com.tomspencerlondon.hexagon.application.SnackService;
import com.tomspencerlondon.hexagon.domain.SnackMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnackConfiguration {

  @Bean
  SnackService snackService() {
    return new SnackService(new SnackMachine());
  }
}
