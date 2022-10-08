package com.tomspencerlondon.snackmachine.hexagon.domain;

import org.jmolecules.ddd.types.ValueObject;

public class SnackPile implements ValueObject {
  private final Snack snack;
  private final int quantity;
  private final double price;

  public SnackPile(Snack snack, int quantity, double price) {
    requirePositiveQuantity(quantity);
    requirePositivePrice(price);

    this.snack = snack;
    this.quantity = quantity;
    this.price = price;
  }

  private void requirePositivePrice(double price) {
    if (price < 0) {
      throw new IllegalArgumentException();
    }
  }

  private void requirePositiveQuantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException();
    }
  }

  public Snack snack() {
    return snack;
  }

  public int quantity() {
    return quantity;
  }

  public double price() {
    return price;
  }

  public SnackPile reduceQuantity() {
    if (quantity == 0) {
      throw new NoSnacksAvailable();
    }
    return new SnackPile(snack, quantity - 1, price);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SnackPile snackPile = (SnackPile) o;

    if (quantity != snackPile.quantity) {
      return false;
    }
    if (Double.compare(snackPile.price, price) != 0) {
      return false;
    }
    return snack.equals(snackPile.snack);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = snack.hashCode();
    result = 31 * result + quantity;
    temp = Double.doubleToLongBits(price);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
