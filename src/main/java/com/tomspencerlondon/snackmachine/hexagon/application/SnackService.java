package com.tomspencerlondon.snackmachine.hexagon.application;

import com.tomspencerlondon.snackmachine.hexagon.application.port.InMemorySnackMachineRepository;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import java.util.Optional;

public class SnackService {
  private InMemorySnackMachineRepository repository;

  public SnackService(InMemorySnackMachineRepository repository) {
    this.repository = repository;
  }

  public Optional<SnackMachine> findById(SnackMachineId id) {
    return repository.findById(id);
  }

  public void save(SnackMachineId id) {

  }
}
