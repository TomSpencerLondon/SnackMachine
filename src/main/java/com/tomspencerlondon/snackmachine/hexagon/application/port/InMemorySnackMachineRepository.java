package com.tomspencerlondon.snackmachine.hexagon.application.port;

import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemorySnackMachineRepository implements SnackMachineRepository {
  private final Map<SnackMachineId, SnackMachine> snackMachines = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong(1);
  private int saveCount = 0;

  @Override
  public Optional<SnackMachine> findById(SnackMachineId snackMachineId) {
    return Optional.ofNullable(snackMachines.get(snackMachineId));
  }

  @Override
  public SnackMachine save(SnackMachine snackMachine) {
    if (snackMachine.getId() == null) {
      snackMachine.setId(SnackMachineId.of(sequence.getAndIncrement()));
    }
    snackMachines.put(snackMachine.getId(), snackMachine);
    saveCount++;
    return snackMachine;
  }

  public int saveCount() {
    return saveCount;
  }
}
