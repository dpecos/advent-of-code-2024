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

    for (int row = 0; row < wordSearch.length; row++) {
      for (int col = 0; col < wordSearch[row].length; col++) {
        if (wordSearch[row][col].equals("X")) {
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
          var offsets = new int[]{-1, 0, 1};

          for (int x : offsets) {
            for (int y : offsets) {
              if (x == 0 && y == 0) {
                continue;
              }

              if (checkMAS(wordSearch, row + x, col + y, x, y)) {
                xmasCount++;
              }
            }
          }
        }
      }
    }

    return xmasCount;
  }

  public static int countCrossMAS(String[][] wordSearch) {
    int masCount = 0;

    var offsets = new int[]{-1, 1};

    for (int row = 0; row < wordSearch.length; row++) {
      for (int col = 0; col < wordSearch[row].length; col++) {

        for (int x : offsets) {
          for (int y : offsets) {
            if (checkCrossMAS(wordSearch, row, col, x, y)) {
              masCount++;
            }
          }
        }
      }
    }

//    System.out.println(Arrays.deepToString(foundCrossMass));

    if (masCount % 2 != 0) {
      throw new IllegalStateException("Found an odd number of MAS");
    }

    return masCount / 2; // every cross-mass is found twice
  }


  private static boolean checkMAS(String[][] wordSearch, int row, int col, int x, int y) {
    return checkPosition(wordSearch, row, col, "M") && checkPosition(wordSearch, row + x, col + y, "A") && checkPosition(wordSearch, row + 2 * x, col + 2 * y, "S");
  }

  static boolean checkCrossMAS(String[][] wordSearch, int row, int col, int x, int y) {
    return checkMAS(wordSearch, row, col, x, y) && (checkMAS(wordSearch, row + x * 2, col, -x, y) || checkMAS(wordSearch, row, col + y * 2, x, -y));
  }

  private static boolean checkPosition(String[][] wordSearch, int row, int col, String letter) {
    if (row < 0 || row >= wordSearch.length || col < 0 || col >= wordSearch[row].length) {
      return false;
    }
    return wordSearch[row][col].equals(letter);
  }
}
