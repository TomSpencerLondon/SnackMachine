package com.tomspencerlondon;

public class Money extends ValueObject<Money> {

  int oneCentCount;
  int fiveCentCount;
  int tenCentCount;
  int quarterCount;
  int oneDollarCount;
  int fiveDollarCount;
  int twentyDollarCount;

  public Money(int oneCentCount, int fiveCentCount, int tenCentCount, int quarterCount, int oneDollarCount, int fiveDollarCount, int twentyDollarCount) {
    if (oneCentCount < 0 ||
        fiveCentCount < 0 ||
        tenCentCount < 0 ||
        quarterCount < 0 ||
        oneDollarCount < 0 ||
        fiveDollarCount < 0 ||
        twentyDollarCount < 0
    ) {
      throw new IllegalArgumentException();
    }
    this.oneCentCount = oneCentCount;
    this.fiveCentCount = fiveCentCount;
    this.tenCentCount = tenCentCount;
    this.quarterCount = quarterCount;
    this.oneDollarCount = oneDollarCount;
    this.fiveDollarCount = fiveDollarCount;
    this.twentyDollarCount = twentyDollarCount;
  }

  public static Money plus(Money money1, Money money2) {
    return new Money(
        money1.oneCentCount + money2.oneCentCount,
        money1.fiveCentCount + money2.fiveCentCount,
        money1.tenCentCount + money2.tenCentCount,
        money1.quarterCount + money2.quarterCount,
        money1.oneDollarCount + money2.oneDollarCount,
        money1.fiveDollarCount + money2.fiveDollarCount,
        money1.twentyDollarCount + money2.twentyDollarCount);
  }

  @Override
  public int getHashCodeCore() {
    int result = oneCentCount;
    result = 31 * result + fiveCentCount;
    result = 31 * result + tenCentCount;
    result = 31 * result + quarterCount;
    result = 31 * result + oneDollarCount;
    result = 31 * result + fiveDollarCount;
    result = 31 * result + twentyDollarCount;
    return result;
  }

  @Override
  protected boolean equalsCore(Money other) {
    Money money = (Money) other;

    if (oneCentCount != money.oneCentCount) {
      return false;
    }
    if (fiveCentCount != money.fiveCentCount) {
      return false;
    }
    if (tenCentCount != money.tenCentCount) {
      return false;
    }
    if (quarterCount != money.quarterCount) {
      return false;
    }
    if (oneDollarCount != money.oneDollarCount) {
      return false;
    }
    if (fiveDollarCount != money.fiveDollarCount) {
      return false;
    }
    return twentyDollarCount == money.twentyDollarCount;
  }

  @Override
  public int hashCode() {
    int result = oneCentCount;
    result = 31 * result + fiveCentCount;
    result = 31 * result + tenCentCount;
    result = 31 * result + quarterCount;
    result = 31 * result + oneDollarCount;
    result = 31 * result + fiveDollarCount;
    result = 31 * result + twentyDollarCount;
    return result;
  }

  @Override
  public String toString() {
    return "Money{" + "oneCentCount=" + oneCentCount + ", fiveCentCount=" + fiveCentCount + ", tenCentCount=" + tenCentCount + ", quarterCount=" + quarterCount + ", oneDollarCount=" + oneDollarCount + ", fiveDollarCount=" + fiveDollarCount + ", twentyDollarCount=" + twentyDollarCount + '}';
  }
}
