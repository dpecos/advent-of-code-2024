package tech.dplabs.aoc.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventureMap {
  public static final int CELL_EMPTY = 0;
  public static final int CELL_WALL = -1;

  private final int[][] cells;

  AdventureMap(int[][] cells) {
    this.cells = cells;
  }

  public static AdventureMap loadFromFile(String path) {
    var lines = 0;
    var columns = 0;
    try (var reader = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines++;
        columns = line.length();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    var cells = new int[lines][columns];

    try (var reader = new BufferedReader(new FileReader(path))) {
      String line;
      int lineNumber = 0;
      while ((line = reader.readLine()) != null) {
        if (line != null) {
          var parts = line.split("");

          for (int i = 0; i < parts.length; i++) {
            switch (parts[i]) {
              case ".":
                cells[lineNumber][i] = 0;
                break;
              case "#":
                cells[lineNumber][i] = -1;
                break;
              case "^":
                cells[lineNumber][i] = 1;
              default:
            }
          }
        }
        lineNumber++;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return new AdventureMap(cells);
  }

  private void printMap() {
    for (int i = 0; i < this.cells.length; i++) {
      for (int j = 0; j < this.cells[i].length; j++) {
        switch (this.cells[i][j]) {
          case 0:
            System.out.print(". ");
            break;
          case -1:
            System.out.print("# ");
            break;
          default:
            System.out.print("* ");
            break;
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public boolean isWithinBoundaries(int x, int y) {
    return x >= 0 && x < this.cells[0].length && y >= 0 && y < this.cells.length;
  }

  public void markVisited(int x, int y, int value) {
    this.cells[y][x] = value;
  }

  public int getCellValue(int x, int y) {
    return this.cells[y][x];
  }

  public int countVisitedCells() {
    var count = 0;
    for (int i = 0; i < this.cells.length; i++) {
      for (int j = 0; j < this.cells[i].length; j++) {
        if (this.cells[i][j] > 0) {
          count += 1;
        }
      }
    }
    return count;
  }

  private int[][] cloneCells(int[][] cells) {
    var newCells = cells.clone();
    for (int k = 0; k < newCells.length; k++) {
      newCells[k] = newCells[k].clone();
    }
    return newCells;
  }

  public int countLoops(Guard guard) {
    var count = 0;

    var originalCells = this.cloneCells(this.cells);

    for (int i = 0; i < this.cells.length; i++) {
      for (int j = 0; j < this.cells[i].length; j++) {
        if (this.cells[i][j] != CELL_EMPTY) {
          continue;
        }

        var cells = this.cloneCells(originalCells);
        cells[i][j] = -1;

        var map = new AdventureMap(cells);

        if (guard.patrol(map)) count += 1;
      }
    }

    return count;
  }

}
