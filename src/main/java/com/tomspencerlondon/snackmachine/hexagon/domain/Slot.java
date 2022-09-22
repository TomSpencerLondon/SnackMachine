package com.tomspencerlondon.snackmachine.hexagon.domain;

public class Slot extends Entity {

  private SnackPile snackPile;
  private final SnackMachine snackMachine;
  private final int position;

  public Slot(SnackPile snackPile, SnackMachine snackMachine, int position) {
    this.snackPile = snackPile;
    this.snackMachine = snackMachine;
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

  public SnackMachine snackMachine() {
    return snackMachine;
  }

  public int position() {
    return position;
  }

  public void reduceQuantity() {
    this.snackPile = snackPile.reduceQuantity();
  }
}
