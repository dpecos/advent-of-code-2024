package tech.dplabs.aoc.day8;

import tech.dplabs.aoc.common.FileUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static tech.dplabs.aoc.common.MatrixUtils.checkPosition;
import static tech.dplabs.aoc.common.MatrixUtils.setPosition;

public class AntennaMap {
  public static final String ANTI_NODE = "#";
  private static final String EMPTY = ".";

  private final String[][] map;
  private final int width;
  private final int height;

  private AntennaMap(int width, int height, String[][] map) {
    this.width = width;
    this.height = height;
    this.map = map;
  }

  public static AntennaMap loadFromFile(String path) {
    var count = FileUtils.countLinesAndLength(path);

    int lines = count[0];
    int length = count[1];

    var map = new String[lines][];

    try (var reader = new BufferedReader(new FileReader(path))) {
      String line;
      int i = 0;
      while ((line = reader.readLine()) != null) {
        map[i] = line.split("");
        i++;
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return new AntennaMap(length, lines, map);
  }

  public String[][] markAntiNodes() {
    var antinodesMap = new String[this.height][this.width];

    for (var row = 0; row < this.height; row++) {
      for (var col = 0; col < this.width; col++) {
        if (checkPosition(this.map, row, col, EMPTY) || checkPosition(this.map, row, col, ANTI_NODE)) {
          continue;
        }

        var offsets = new int[]{-1, 0, 1};

        for (int x : offsets) {
          for (int y : offsets) {
            if (x == 0 && y == 0) {
              continue;
            }

            var frequency = this.map[row][col];

            for (var i = 1; i < this.height; i++) {
              for (var j = 1; j < this.width; j++) {
                if (checkPosition(this.map, row + x * i, col + y * j, frequency)) {
                  setPosition(antinodesMap, row + x * i * 2, col + y * j * 2, ANTI_NODE);
                }
              }
            }
          }
        }
      }
    }
    return antinodesMap;
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }
}
