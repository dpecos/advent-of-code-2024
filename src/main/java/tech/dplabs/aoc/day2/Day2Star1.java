import tech.dplabs.aoc.common.RunUtils;

import static tech.dplabs.aoc.day2.ReportUtils.isReportSafe;
import static tech.dplabs.aoc.day2.ReportUtils.readReportsFromFile;

void main() {
  RunUtils.measureRuntime("Day 2 - Part 1", () -> {
    var reports = readReportsFromFile("src/main/resources/day2_input.txt");

    var safeReports = 0;

    for (var report : reports) {
      if (isReportSafe(report)) {
        safeReports++;
      }
    }

    System.out.println(safeReports);
  });
}
