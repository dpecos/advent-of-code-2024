package tech.dplabs.aoc.day2;

import static tech.dplabs.aoc.day2.ReportUtils.isReportSafe;
import static tech.dplabs.aoc.day2.ReportUtils.readReportsFromFile;

public class Day2Star1 {

  public static void main(String[] args) {
    var reports = readReportsFromFile("src/main/resources/day2_input.txt");

    var safeReports = 0;

    for (var report : reports) {
      if (isReportSafe(report)) {
        safeReports++;
      }
    }

    System.out.println(safeReports);
  }
}
