package com.tomspencerlondon.snackmachine;

import com.tomspencerlondon.snackmachine.hexagon.application.SnackService;
import com.tomspencerlondon.snackmachine.hexagon.application.port.InMemorySnackMachineRepository;
import com.tomspencerlondon.snackmachine.hexagon.application.port.SnackMachineRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnackConfiguration {

  @Bean
  SnackService snackService(SnackMachineRepository repository) {
    return new SnackService(repository);
  }

  @Bean
  SnackMachineRepository snackMachineRepository() {
    return new InMemorySnackMachineRepository();
  }
}
