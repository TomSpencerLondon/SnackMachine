package com.tomspencerlondon.snackmachine;


import com.tomspencerlondon.snackmachine.hexagon.application.port.SnackMachineRepository;
import com.tomspencerlondon.snackmachine.hexagon.domain.Slot;
import com.tomspencerlondon.snackmachine.hexagon.domain.SlotId;
import com.tomspencerlondon.snackmachine.hexagon.domain.Snack;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackId;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackPile;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SnackMachineApplicationDbInit implements CommandLineRunner {

  private final SnackMachineRepository repository;

  public SnackMachineApplicationDbInit(SnackMachineRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    repository.save(new SnackMachine(List.of(new Slot(SlotId.of(1L), new SnackPile(new Snack(SnackId.of(1L), "Coke"), 10, 0.0), 1), new Slot(SlotId.of(1L), new SnackPile(new Snack(SnackId.of(1L), "Crisps"), 10, 0.0), 2), new Slot(SlotId.of(1L), new SnackPile(new Snack(SnackId.of(1L), "Chocolate"), 10, 0.0), 3))));
  }
}
