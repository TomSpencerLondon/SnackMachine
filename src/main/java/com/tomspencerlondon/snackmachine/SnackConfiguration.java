package com.tomspencerlondon.snackmachine;

import com.tomspencerlondon.snackmachine.hexagon.application.SnackService;
import com.tomspencerlondon.snackmachine.hexagon.application.port.InMemorySnackMachineRepository;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnackConfiguration {

  @Bean
  SnackService snackService() {
    InMemorySnackMachineRepository repository = new InMemorySnackMachineRepository();
    repository.save(new SnackMachine());
    return new SnackService(repository);
  }
}
