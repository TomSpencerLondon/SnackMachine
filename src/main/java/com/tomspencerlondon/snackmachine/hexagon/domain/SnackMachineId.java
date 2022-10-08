package com.tomspencerlondon.snackmachine.hexagon.domain;

import org.jmolecules.ddd.types.Identifier;

public record SnackMachineId(long id) implements Identifier {
  public static SnackMachineId of(long id) {
    return new SnackMachineId(id);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "=" + id;
  }
}
