package com.tomspencerlondon.snackmachine.hexagon.domain;

import org.jmolecules.ddd.types.Entity;

public class Slot implements Entity<SnackMachine, SlotId> {

  private SlotId slotId;
  private SnackPile snackPile;
  private final int position;

  public Slot(SlotId slotId, SnackPile snackPile, int position) {
    this.slotId = slotId;
    this.snackPile = snackPile;
    this.position = position;
  }

  public SnackPile snackPile() {
    return snackPile;
  }

  public Snack snack() {
    return snackPile.snack();
  }

  public int quantity() {
    return snackPile.quantity();
  }

  public double price() {
    return snackPile.price();
  }

  public int position() {
    return position;
  }

  public void reduceQuantity() {
    snackPile = snackPile.reduceQuantity();
  }

  @Override
  public SlotId getId() {
    return slotId;
  }
}
