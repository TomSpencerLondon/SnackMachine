package com.tomspencerlondon.snackmachine;

import com.tomspencerlondon.snackmachine.hexagon.application.SnackService;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnackConfiguration {

  @Bean
  SnackService snackService() {
    return new SnackService(new SnackMachine());
  }
}
