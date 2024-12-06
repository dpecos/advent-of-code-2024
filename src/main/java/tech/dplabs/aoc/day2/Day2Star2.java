import tech.dplabs.aoc.common.RunUtils;

import static tech.dplabs.aoc.day2.ReportUtils.isReportSafe;
import static tech.dplabs.aoc.day2.ReportUtils.readReportsFromFile;

void main() {
  RunUtils.measureRuntime("Day 2 - Part 2", () -> {
    var startTime = System.currentTimeMillis();

    var reports = readReportsFromFile("src/main/resources/day2_input.txt");

    var safeReports = 0;

    /* Single threaded */
//    for (var report : reports) {
//      if (isReportSafe(report, 1)) {
//        safeReports++;
//      }
//    }

    /* Virtual threads - Executors API */
//    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
//      var jobs = new ArrayList<Future<Boolean>>();
//
//      for (var report : reports) {
//        jobs.add(executor.submit(() -> {
//            return isReportSafe(report, 1);
//          })
//        );
//      }
//
//      for (var job : jobs) {
//        try {
//          if (job.get()) {
//            safeReports++;
//          }
//        } catch (Exception e) {
//          throw new RuntimeException(e);
//        }
//      }
//    }

    /* Virtual threads - Structured Tasks API */
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      var jobs = new ArrayList<Supplier<Boolean>>();

      for (var report : reports) {
        jobs.add(scope.fork(() -> isReportSafe(report, 1)));
      }

      scope.join();
      scope.throwIfFailed();

      for (var job : jobs) {
        try {
          if (job.get()) {
            safeReports++;
          }
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }

    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println(safeReports);

    System.out.println("Total execution time: " + (System.currentTimeMillis() - startTime) + "ms");
  });
}
