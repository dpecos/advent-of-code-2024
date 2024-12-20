package tech.dplabs.aoc.common;

public class MatrixUtils {
  public static <T> boolean withinBoundaries(T[][] matrix, int row, int col) {
    return (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length);
  }

  public static <T> boolean checkPosition(T[][] matrix, int row, int col, T value) {
    if (withinBoundaries(matrix, row, col)) {
      return false;
    }
    return matrix[row][col].equals(value);
  }

  public static <T> T[][] setPosition(T[][] matrix, int row, int col, T value) {
    if (withinBoundaries(matrix, row, col)) {
      return matrix;
    }
    matrix[row][col] = value;
    return matrix;
  }

  public static <T> void print(T[][] matrix) {
    for (var row : matrix) {
      for (var col : row) {
        System.out.print(" " + (col != null ? col : ".") + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static <T> int countValues(T[][] matrix, T value) {
    int count = 0;
    for (var row : matrix) {
      for (var col : row) {
        if (col != null && col.equals(value)) {
          count++;
        }
      }
    }
    return count;
  }
}
