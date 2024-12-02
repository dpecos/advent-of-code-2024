package tech.dplabs.aoc.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportUtils {
  public static List<List<Integer>> readReportsFromFile(String path) {
    var reports = new ArrayList<List<Integer>>();

    try (var reader = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = reader.readLine()) != null) {
        var parts = line.split("\s+");

        reports.add(Arrays.stream(parts).map(Integer::parseInt).toList());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return reports;
  }

  public static boolean isReportSafe(List<Integer> report) {
    var isSafe = true;

    Integer levelDeltaDirection = null;
    Integer previousLevel = null;

    for (var level : report) {
      if (previousLevel != null) {
        var currentDeltaDirection = level - previousLevel > 0 ? 1 : -1;
        if (levelDeltaDirection == null) {
          levelDeltaDirection = currentDeltaDirection;
        }

        if (currentDeltaDirection != levelDeltaDirection) {
          isSafe = false;
          break;
        }

        if (Math.abs(level - previousLevel) <= 0 || Math.abs(level - previousLevel) > 3) {
          isSafe = false;
          break;
        }
      }
      previousLevel = level;
    }

    return isSafe;
  }
}
