package com.tomspencerlondon.snackmachine.hexagon.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.tomspencerlondon.snackmachine.hexagon.application.port.InMemorySnackMachineRepository;
import com.tomspencerlondon.snackmachine.hexagon.domain.Slot;
import com.tomspencerlondon.snackmachine.hexagon.domain.Snack;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackPile;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class SnackServiceTest {

  @Test
  void findsSnackMachineById() {
    SnackMachine snackMachine = new SnackMachine(List.of(new Slot(new SnackPile(new Snack("Coke"), 10, 0.0), 1), new Slot(new SnackPile(new Snack("Crisps"), 10, 0.0), 2), new Slot(new SnackPile(new Snack("Chocolate"), 10, 0.0), 3)));
    InMemorySnackMachineRepository repository = new InMemorySnackMachineRepository();
    SnackMachine savedSnackMachine = repository.save(snackMachine);
    SnackService snackService = new SnackService(repository);

    Optional<SnackMachine> result = snackService.findById(SnackMachineId.of(1L));

    assertThat(result)
        .contains(savedSnackMachine);
  }
}
