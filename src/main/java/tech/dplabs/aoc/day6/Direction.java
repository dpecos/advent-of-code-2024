package tech.dplabs.aoc.day6;

public enum Direction {
  UP, DOWN, LEFT, RIGHT;

  public int[] vector() {
    return switch (this) {
      case UP -> new int[]{-1, 0};
      case DOWN -> new int[]{1, 0};
      case LEFT -> new int[]{0, -1};
      case RIGHT -> new int[]{0, 1};
    };
  }

  public int id() {
    return switch (this) {
      case UP -> 1;
      case RIGHT -> 2;
      case DOWN -> 3;
      case LEFT -> 4;
    };
  }
}
