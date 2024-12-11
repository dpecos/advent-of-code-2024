package tech.dplabs.aoc.day11;

public record Stone(long value) {
  public Stone[] blink() {
    int numberDigits = (int) (Math.log10(value) + 1);
    if (value == 0) {
      return new Stone[]{new Stone(1)};
    } else if (numberDigits % 2 == 0) {
      var leftValue = value / (long) Math.pow(10, numberDigits / 2);
      var rightValue = value % (long) Math.pow(10, numberDigits / 2);
      return new Stone[]{new Stone(leftValue), new Stone(rightValue)};
    } else {
      return new Stone[]{new Stone(value * 2024L)};
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
