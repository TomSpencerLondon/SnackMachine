package com.tomspencerlondon.snackmachine.hexagon.application.port;

import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import java.lang.reflect.Member;
import java.util.Optional;

public interface SnackMachineRepository {
  Optional<SnackMachine> findById(SnackMachineId ensembleId);

  SnackMachine save(SnackMachine member);
}
