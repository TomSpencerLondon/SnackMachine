package com.tomspencerlondon.snackmachine.hexagon.domain;

import org.jmolecules.ddd.types.ValueObject;

public record SnackPile(Snack snack, int quantity, double price) implements ValueObject {

  public SnackPile {
    requirePositiveQuantity(quantity);
    requirePositivePrice(price);
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

}
