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
        var parts = line.split(" +");

        reports.add(Arrays.stream(parts).map(Integer::parseInt).toList());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return reports;
  }

  public static boolean isReportSafe(List<Integer> report, int toolerateUnsafeCount) {
    var isSafe = true;

    Integer levelDeltaDirection = null;
    Integer previousLevel = null;

    for (int i = 0; i < report.size(); i++) {
      var level = report.get(i);
      if (previousLevel != null) {
        var currentDeltaDirection = level - previousLevel > 0 ? 1 : -1;
        if (levelDeltaDirection == null) {
          levelDeltaDirection = currentDeltaDirection;
        }

        if (currentDeltaDirection != levelDeltaDirection) {
          isSafe = false;
        }

        if (Math.abs(level - previousLevel) <= 0 || Math.abs(level - previousLevel) > 3) {
          isSafe = false;
        }

        if (!isSafe) {
          if (toolerateUnsafeCount > 0) {
            toolerateUnsafeCount--;

            for (int j = 0; j <= report.size(); j++) {
              var partialReport = new ArrayList<>(report.subList(0, j == 0 ? 0 : j - 1));
              partialReport.addAll(report.subList(j, report.size()));

              isSafe = isReportSafe(partialReport, toolerateUnsafeCount);

              if (isSafe) {
                // if the partial report is safe, no need to continue checking
                return true;
              }
            }
          }

          if (!isSafe) {
            break;
          }
        }
      }
      previousLevel = level;
    }

    return isSafe;
  }

  public static boolean isReportSafe(List<Integer> report) {
    return isReportSafe(report, 0);
  }
}
