package com.tomspencerlondon.snackmachine;

import com.tomspencerlondon.snackmachine.hexagon.application.SnackService;
import com.tomspencerlondon.snackmachine.hexagon.application.port.InMemorySnackMachineRepository;
import com.tomspencerlondon.snackmachine.hexagon.domain.Slot;
import com.tomspencerlondon.snackmachine.hexagon.domain.SlotId;
import com.tomspencerlondon.snackmachine.hexagon.domain.Snack;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackId;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackPile;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnackConfiguration {

  @Bean
  SnackService snackService() {
    InMemorySnackMachineRepository repository = new InMemorySnackMachineRepository();
    repository.save(new SnackMachine(List.of(new Slot(SlotId.of(1L), new SnackPile(new Snack(SnackId.of(1L), "Coke"), 10, 0.0), 1), new Slot(SlotId.of(1L), new SnackPile(new Snack(SnackId.of(1L), "Crisps"), 10, 0.0), 2), new Slot(SlotId.of(1L), new SnackPile(new Snack(SnackId.of(1L), "Chocolate"), 10, 0.0), 3))));
    return new SnackService(repository);
  }
}
