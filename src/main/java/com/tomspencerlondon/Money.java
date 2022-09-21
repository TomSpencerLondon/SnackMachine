package com.tomspencerlondon;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money extends ValueObject<Money> {

  public static Money ZERO = new Money(0, 0, 0, 0, 0, 0, 0);
  public static Money ONE_CENT = new Money(1, 0, 0, 0, 0, 0, 0);
  public static Money FIVE_CENT = new Money(0, 1, 0, 0, 0, 0, 0);
  public static Money TEN_CENT = new Money(0, 0, 1, 0, 0, 0, 0);
  public static Money QUARTER_CENT = new Money(0, 0, 0, 1, 0, 0, 0);
  public static Money ONE_DOLLAR = new Money(0, 0, 0, 0, 1, 0, 0);
  public static Money FIVE_DOLLAR = new Money(0, 0, 0, 0, 0, 1, 0);
  public static Money TWENTY_DOLLAR = new Money(0, 0, 0, 0, 0, 0, 1);

  private final int oneCentCount;
  private final int fiveCentCount;
  private final int tenCentCount;
  private final int quarterCount;
  private final int oneDollarCount;
  private final int fiveDollarCount;
  private final int twentyDollarCount;

  public Money(int oneCentCount, int fiveCentCount, int tenCentCount, int quarterCount, int oneDollarCount, int fiveDollarCount, int twentyDollarCount) {
    requirePositiveCount(
        oneCentCount, fiveCentCount,
        tenCentCount, quarterCount,
        oneDollarCount, fiveDollarCount,
        twentyDollarCount);
    this.oneCentCount = oneCentCount;
    this.fiveCentCount = fiveCentCount;
    this.tenCentCount = tenCentCount;
    this.quarterCount = quarterCount;
    this.oneDollarCount = oneDollarCount;
    this.fiveDollarCount = fiveDollarCount;
    this.twentyDollarCount = twentyDollarCount;
  }

  public static Money multiply(Money money, int multiplicand) {
    return new Money(
        money.oneCentCount * multiplicand,
        money.fiveCentCount * multiplicand,
        money.tenCentCount * multiplicand,
        money.quarterCount * multiplicand,
        money.oneDollarCount * multiplicand,
        money.fiveDollarCount * multiplicand,
        money.twentyDollarCount * multiplicand
        );
  }

  private void requirePositiveCount(int oneCentCount, int fiveCentCount, int tenCentCount, int quarterCount, int oneDollarCount, int fiveDollarCount, int twentyDollarCount) {
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

  public static Money subtract(Money money1, Money money2) {
    return new Money(
        money1.oneCentCount - money2.oneCentCount,
        money1.fiveCentCount - money2.fiveCentCount,
        money1.tenCentCount - money2.tenCentCount,
        money1.quarterCount - money2.quarterCount,
        money1.oneDollarCount - money2.oneDollarCount,
        money1.fiveDollarCount - money2.fiveDollarCount,
        money1.twentyDollarCount - money2.twentyDollarCount);
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

  public double amount() {
    double value = oneCentCount * 0.01 +
        fiveCentCount * 0.05 +
        tenCentCount * 0.10 +
        quarterCount *  0.25 +
        oneDollarCount +
        fiveDollarCount * 5 +
        twentyDollarCount * 20;
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
