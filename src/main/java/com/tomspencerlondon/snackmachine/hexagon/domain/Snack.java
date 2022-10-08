package com.tomspencerlondon.snackmachine.hexagon.domain;
import org.jmolecules.ddd.types.AggregateRoot;

public class Snack implements AggregateRoot<Snack, SnackId> {

  private SnackId id;
  private final String name;

  public Snack(SnackId id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public SnackId getId() {
    return id;
  }

  public String name() {
    return name;
  }
}
