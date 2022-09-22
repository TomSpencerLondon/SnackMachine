package com.tomspencerlondon.snackmachine.hexagon.domain;

public class Snack extends AggregateRoot {
  private final String name;

  public Snack(String name) {
    this.name = name;
  }
}
