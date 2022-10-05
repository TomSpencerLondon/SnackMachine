package com.tomspencerlondon.snackmachine.hexagon.domain;

public class Slot extends Entity {

  private SnackPile snackPile;
  private final int position;

  public Slot(SnackPile snackPile, int position) {
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
}
