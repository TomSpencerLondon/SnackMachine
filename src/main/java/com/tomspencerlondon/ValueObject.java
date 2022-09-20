package com.tomspencerlondon;

public abstract class ValueObject<T> {

  @Override
  public int hashCode() {
    return getHashCodeCore();
  }

  protected abstract int getHashCodeCore();

  @Override
  public boolean equals(Object obj) {
    var valueObject = (T) obj;

    if (valueObject == null) {
      return false;
    }
    return equalsCore(valueObject);
  }



  protected abstract boolean equalsCore(T obj);
}
