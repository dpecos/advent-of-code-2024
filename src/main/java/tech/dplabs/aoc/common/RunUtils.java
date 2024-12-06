package tech.dplabs.aoc.common;

public class RunUtils {
  public static void measureRuntime(Runnable runnable) {
    long start = System.currentTimeMillis();
    runnable.run();
    long end = System.currentTimeMillis();
    System.out.println("** Execution time: " + (end - start) / 1000f + " sec **");
  }
}
