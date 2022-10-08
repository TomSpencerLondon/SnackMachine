package com.tomspencerlondon.snackmachine.hexagon.application;

import com.tomspencerlondon.snackmachine.hexagon.application.port.SnackMachineRepository;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import java.util.List;
import java.util.Optional;

public class SnackService {
  private SnackMachineRepository repository;

  public SnackService(SnackMachineRepository repository) {
    this.repository = repository;
  }

  public Optional<SnackMachine> findById(SnackMachineId id) {
    return repository.findById(id);
  }


  public List<SnackMachine> findAll() {
    return repository.findAll();
  }
}
