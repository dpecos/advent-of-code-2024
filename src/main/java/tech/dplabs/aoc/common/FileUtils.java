package tech.dplabs.aoc.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
  public static int[] countLinesAndLength(String path) {
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

    return new int[]{lines, length};
  }
}
