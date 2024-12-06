package tech.dplabs.aoc.day6;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

enum Direction {
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

public record Guard(int x, int y, Direction direction) {
  public static Guard loadFromFile(String path) {

    try (var reader = new BufferedReader(new FileReader(path))) {
      String line;
      int lineNumber = 0;
      while ((line = reader.readLine()) != null) {
        if (line != null) {
          var parts = line.split("");

          for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("^")) {
              return new Guard(i, lineNumber, Direction.UP);
            }
          }
        }
        lineNumber++;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return null;
  }

  public Guard turn() {
    var newDirection = switch (direction) {
      case UP -> Direction.RIGHT;
      case RIGHT -> Direction.DOWN;
      case DOWN -> Direction.LEFT;
      case LEFT -> Direction.UP;
    };
    return new Guard(x, y, newDirection);
  }

  public Guard step() {
    var vector = direction.vector();
    return new Guard(x + vector[1], y + vector[0], direction);
  }

  public boolean patrol(AdventureMap map) {
    var withinMap = true;

    var guard = this;

    while (withinMap) {
      var nextGuard = guard.step();
      var x = nextGuard.x();
      var y = nextGuard.y();

      withinMap = map.withinBoundaries(x, y);
      if (withinMap) {
        var directionValueForCell = nextGuard.direction.id();
        var cellValue = map.getCellValue(x, y);
        if (directionValueForCell == cellValue) {
          return true;
        }

        switch (cellValue) {
          case AdventureMap.CELL_EMPTY:
            map.markVisited(x, y, directionValueForCell);
            break;
          case AdventureMap.CELL_WALL:
            nextGuard = guard.turn();
            break;
        }
        guard = nextGuard;
      }
    }

    return false;
  }
}
