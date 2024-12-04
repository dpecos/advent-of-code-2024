package tech.dplabs.aoc.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordSearch {
  public static String[][] loadFromFile(String path) {
    // let's count the length and amount of lines in the file
    int lines = 0;
    int length = 0;
    try (var reader = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (length == 0) {
          length = line.length();
        } else if (length != line.length()) {
          throw new IllegalArgumentException("All lines must have the same length");
        }
        lines++;
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    var wordle = new String[lines][];

    try (var reader = new BufferedReader(new FileReader(path))) {
      String line;
      int i = 0;
      while ((line = reader.readLine()) != null) {
        wordle[i] = line.split("");
        i++;
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return wordle;
  }

  public static int countXMAS(String[][] wordSearch) {
    int xmasCount = 0;

    for (int i = 0; i < wordSearch.length; i++) {
      for (int j = 0; j < wordSearch[i].length; j++) {
        if (wordSearch[i][j].equals("X")) {
          /*
            1 2 3
            4 X 6
            7 8 9

            0 1 2
            3 X 5
            6 7 8

            i-1,j-1   i-1,j   i-1,j+1
            i,j-1     i,j     i,j+1
            i+1,j-1   i+1,j   i+1,j+1
           */
          xmasCount += findMAS(wordSearch, i, j);
        }
      }
    }

    return xmasCount;
  }

  private static int findMAS(String[][] wordSearch, int i, int j) {
    if (i < 0 || i >= wordSearch.length || j < 0 || j >= wordSearch[i].length) {
      return 0;
    }
    int count = 0;

    var offsets = new int[]{-1, 0, 1};

    for (int x : offsets) {
      for (int y : offsets) {
        if (x == 0 && y == 0) {
          continue;
        }

        if (checkPosition(wordSearch, i + x, j + y, "M")) {
          if (checkPosition(wordSearch, i + 2 * x, j + 2 * y, "A")) {
            if (checkPosition(wordSearch, i + 3 * x, j + 3 * y, "S")) {
              count++;
            }
          }
        }
      }
    }

    return count;
  }

  private static boolean checkPosition(String[][] wordSearch, int i, int j, String letter) {
    if (i < 0 || i >= wordSearch.length || j < 0 || j >= wordSearch[i].length) {
      return false;
    }
    return wordSearch[i][j].equals(letter);
  }
}
