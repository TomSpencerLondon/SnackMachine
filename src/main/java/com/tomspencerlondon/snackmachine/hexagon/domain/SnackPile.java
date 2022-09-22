package com.tomspencerlondon.snackmachine.hexagon.domain;

public class SnackPile extends ValueObject<SnackPile> {
  private final Snack snack;
  private int quantity;
  private final double price;

  public SnackPile(Snack snack, int quantity, double price) {
    requirePositiveQuantityAndPrice(quantity, price);

    this.snack = snack;
    this.quantity = quantity;
    this.price = price;
  }

  private void requirePositiveQuantityAndPrice(int quantity, double price) {
    if (quantity < 0) {
      throw new IllegalArgumentException();
    }
    if (price < 0) {
      throw new IllegalArgumentException();
    }
    if (price % 0.01 > 0) {
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
    return new SnackPile(snack, quantity - 1, price);
  }

  @Override
  protected int getHashCodeCore() {
    return 0;
  }

  @Override
  protected boolean equalsCore(SnackPile obj) {
    return false;
  }
}
