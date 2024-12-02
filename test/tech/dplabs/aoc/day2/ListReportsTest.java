package tech.dplabs.aoc.day2;

import org.junit.jupiter.api.Test;
import tech.dplabs.aoc.day1.ListUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListReportsTest {

  @Test
  void testReadingReportsFromFile() {
    var reports = ReportUtils.readReportsFromFile("test/tech/dplabs/aoc/day2/input.txt");

    var report1 = List.of(7,6,4,2,1);
    var report6 = List.of(1, 3, 6, 7, 9);

    assertEquals(report1, reports.get(0));
    assertEquals(report6, reports.get(5));
  }

  @Test
  void testReportSafety() {
    var report1 = List.of(7,6,4,2,1);
    assertTrue(ReportUtils.isReportSafe(List.of(7, 6, 4, 2, 1)));
    assertFalse(ReportUtils.isReportSafe(List.of(1, 2, 7, 8, 9)));
    assertFalse(ReportUtils.isReportSafe(List.of(1, 3, 2, 4, 5)));
    assertFalse(ReportUtils.isReportSafe(List.of(8, 6, 4, 4, 1)));
    assertTrue(ReportUtils.isReportSafe(List.of(1, 3, 6, 7, 9)));
  }
}
