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
  protected int getHashCodeCore() {
    int hashCode = oneCentCount;
    hashCode = (hashCode * 397) ^ fiveCentCount;
    hashCode = (hashCode * 397) ^ tenCentCount;
    hashCode = (hashCode * 397) ^ quarterCount;
    hashCode = (hashCode * 397) ^ oneDollarCount;
    hashCode = (hashCode * 397) ^ fiveDollarCount;
    hashCode = (hashCode * 397) ^ twentyDollarCount;
    return hashCode;
  }

  @Override
  protected boolean equalsCore(Money other) {
    return oneCentCount == other.oneCentCount
    && tenCentCount == other.tenCentCount
    && quarterCount == other.quarterCount
    && oneDollarCount == other.oneDollarCount
    && fiveDollarCount == other.fiveDollarCount
    && twentyDollarCount == other.oneCentCount;
  }
}
