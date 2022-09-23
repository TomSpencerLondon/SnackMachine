package com.tomspencerlondon.snackmachine.hexagon.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.tomspencerlondon.snackmachine.hexagon.application.port.InMemorySnackMachineRepository;
import com.tomspencerlondon.snackmachine.hexagon.domain.Money;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class SnackServiceTest {

  @Test
  void findsSnackMachineById() {
    SnackMachine snackMachine = new SnackMachine();
    InMemorySnackMachineRepository repository = new InMemorySnackMachineRepository();
    SnackMachine savedSnackMachine = repository.save(snackMachine);
    SnackService snackService = new SnackService(repository);

    Optional<SnackMachine> result = snackService.findById(SnackMachineId.of(1L));

    assertThat(result)
        .contains(savedSnackMachine);
  }
}
