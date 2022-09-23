package com.tomspencerlondon.snackmachine.hexagon.domain;

public record SnackMachineId(long id) {
  public static SnackMachineId of(long id) {
    return new SnackMachineId(id);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "=" + id;
  }
}
